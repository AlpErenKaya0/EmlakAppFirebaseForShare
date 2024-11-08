package com.example.emlakappfirebase.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emlakappfirebase.koin.di.repos.FavouriteRepository
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomFavouriteViewModel(
    private val favouriteRepository: FavouriteRepository
) : ViewModel() {

    private val _favouriteHouseDataList = mutableStateOf<List<HouseData>>(emptyList())
    val favouriteHouseDataList: State<List<HouseData>> = _favouriteHouseDataList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        refreshFavourites()
    }

    fun refreshFavourites() {
        viewModelScope.launch {
            _isRefreshing.value = true
            loadUserFavourites()
            _isRefreshing.value = false
        }
    }

    private suspend fun loadUserFavourites() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            try {
                val favourites = favouriteRepository.getUserFavourites(currentUser.uid)
                _favouriteHouseDataList.value = favourites
                Log.d("BottomFavouriteViewModel", "Favoriler yüklendi: ${favourites.size} ev bulundu")
            } catch (e: Exception) {
                Log.e("BottomFavouriteViewModel", "Favori verileri yüklenirken hata oluştu", e)
            }
        } else {
            Log.d("BottomFavouriteViewModel", "Kullanıcı oturum açmamış")
        }
    }
}
