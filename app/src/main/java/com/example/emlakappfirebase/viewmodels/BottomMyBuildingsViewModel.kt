package com.example.emlakappfirebase.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emlakappfirebase.koin.di.repos.MyBuildingRepository
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomMyBuildingsViewModel(
    private val myBuildingRepository: MyBuildingRepository
) : ViewModel() {
    private val _userBuildings = mutableStateOf<List<HouseData>>(emptyList())
    val userBuildings: State<List<HouseData>> = _userBuildings

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        refreshUserBuildings() // Başlangıçta kullanıcı binalarını yükle
    }

    fun refreshUserBuildings() {
        viewModelScope.launch {
            _isRefreshing.value = true
            loadUserBuildings()
            _isRefreshing.value = false
        }
    }

    private fun loadUserBuildings() {
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                try {
                    val buildings = myBuildingRepository.getUserBuildings(currentUser.uid)
                    _userBuildings.value = buildings
                    Log.d(
                        "BottomMyBuildingsViewModel",
                        "Kullanıcı binaları yüklendi: ${buildings.size} bina bulundu"
                    )
                } catch (e: Exception) {
                    Log.e("BottomMyBuildingsViewModel", "Binalar yüklenirken hata oluştu", e)
                }
            } else {
                Log.d("BottomMyBuildingsViewModel", "Kullanıcı oturum açmamış")
            }
        }
    }

    fun deleteBuilding(houseData: HouseData) {
        viewModelScope.launch {
            try {
                myBuildingRepository.deleteBuilding(houseData)
                Log.d("BottomMyBuildingsViewModel", "Bina başarıyla silindi: ${houseData.name}")
            } catch (e: Exception) {
                Log.e("BottomMyBuildingsViewModel", "Bina silinirken hata oluştu", e)
            }
        }
    }

    fun removeBuildingFromList(houseData: HouseData) {
        _userBuildings.value = _userBuildings.value.filter { it != houseData }
    }
}

//class BottomMyBuildingsViewModel(
//    private val myBuildingRepository: MyBuildingRepository
//) : ViewModel() {
//    private val _userBuildings = mutableStateOf<List<HouseData>>(emptyList())
//    val userBuildings: State<List<HouseData>> = _userBuildings
//
//    init {
//        loadUserBuildings()
//    }
//
//    private fun loadUserBuildings() {
//        viewModelScope.launch {
//            val currentUser = FirebaseAuth.getInstance().currentUser
//            if (currentUser != null) {
//                try {
//                    val buildings = myBuildingRepository.getUserBuildings(currentUser.uid)
//                    _userBuildings.value = buildings
//                    Log.d("BottomMyBuildingsViewModel", "Kullanıcı binaları yüklendi: ${buildings.size} bina bulundu")
//                } catch (e: Exception) {
//                    Log.e("BottomMyBuildingsViewModel", "Binalar yüklenirken hata oluştu", e)
//                }
//            } else {
//                Log.d("BottomMyBuildingsViewModel", "Kullanıcı oturum açmamış")
//            }
//        }
//    }
//}
