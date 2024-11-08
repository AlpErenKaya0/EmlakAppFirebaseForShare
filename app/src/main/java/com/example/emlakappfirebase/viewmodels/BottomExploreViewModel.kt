package com.example.emlakappfirebase.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emlakappfirebase.koin.di.repos.ExploreRepository
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class BottomExploreViewModel(
    private val exploreRepository: ExploreRepository
) : ViewModel() {

    private val _houseDataList = mutableStateOf<List<HouseData>>(emptyList())
    val houseDataList: State<List<HouseData>> = _houseDataList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing





    init {
        refreshHouseData()
    }

    fun refreshHouseData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            loadPublicHouseData()
            _isRefreshing.value = false
        }
    }

    private fun loadPublicHouseData() {
        viewModelScope.launch {
            try {
                val houseList = exploreRepository.loadPublicData()
                _houseDataList.value = houseList
                Log.d("BottomExploreViewModel", "Veriler yüklendi: ${houseList.size} ev bulundu")
            } catch (e: Exception) {
                Log.e("BottomExploreViewModel", "Veri yüklenirken hata oluştu", e)
            }
        }
    }

    fun onHeartClick(houseData: HouseData, isFilled: Boolean) {
        viewModelScope.launch {
            exploreRepository.onHeartClick(houseData, isFilled)
        }
    }


//    fun toggleFavorite(houseData: HouseData, isFilled: Boolean) {
//        viewModelScope.launch {
//            val favouriteData = houseDataToFavouriteData(houseData)
//            if (isFilled) {
//                favouriteDao.addFavorite(favouriteData) // Favorilere ekle
//                Log.d("BottomExploreViewModel", "Ev favorilere eklendi")
//            } else {
//                favouriteDao.deleteFavorite(favouriteData) // Favorilerden çıkar
//                Log.d("BottomExploreViewModel", "Ev favorilerden çıkarıldı")
//            }
//        }
//    }
//}
//fun houseDataToFavouriteData(houseData: HouseData): FavouriteData {
//    return FavouriteData(
//        userId = houseData.userId,
//        title = houseData.title,
//        name = houseData.name,
//        address = houseData.address,
//        photos = houseData.photos,
//        positiveTitle = houseData.positiveTitle,
//        negativeTitle = houseData.negativeTitle,
//        price = houseData.price,
//        isPrivate = houseData.isPrivate
//    )
}