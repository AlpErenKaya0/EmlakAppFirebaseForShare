package com.example.emlakappfirebase.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.emlakappfirebase.koin.di.repos.MyBuildingRepository
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.example.emlakappfirebase.utils.HouseCard
import com.example.emlakappfirebase.utils.HouseCardHeartless
import com.example.emlakappfirebase.utils.HouseCardWithTrash
import com.example.emlakappfirebase.viewmodels.BottomMyBuildingsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
@Composable
fun BottomNavigationMyBuildingScreen(
    modifier: Modifier = Modifier,
    myBuildingsViewModel: BottomMyBuildingsViewModel = koinViewModel()
) {
    val userBuildings by myBuildingsViewModel.userBuildings
    val isRefreshing by myBuildingsViewModel.isRefreshing.collectAsState()
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var selectedBuilding: HouseData? by remember { mutableStateOf(null) }

    // Sayfa her açıldığında refresh yap
    LaunchedEffect(Unit) {
        myBuildingsViewModel.refreshUserBuildings()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { myBuildingsViewModel.refreshUserBuildings() },
        modifier = modifier
    ) {
        if (userBuildings.isEmpty()) {
            Text(text = "No data available", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        } else {
            LazyColumn {
                items(userBuildings) { houseData ->
                    val imageUris = houseData.photos.mapNotNull { url ->
                        try {
                            Uri.parse(url)
                        } catch (e: Exception) {
                            null
                        }
                    }

                    HouseCardWithTrash(
                        name = houseData.name ?: "",
                        imageUrls = imageUris,
                        topText = houseData.title ?: "",
                        middleText = houseData.address ?: "",
                        bottomText = houseData.price ?: "",
                        onTrashClick = { isFilled ->
                            if (isFilled) {
                                selectedBuilding = houseData
                                showDeleteConfirmation = true
                            }
                        },
                        onCardClick = {
                            println("Binaya tıklandı: ${houseData.name}")
                        }
                    )
                }
            }
        }
    }

    // Silme onayı için AlertDialog
    if (showDeleteConfirmation && selectedBuilding != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text(text = "Veriyi Sil") },
            text = { Text("Eğer silerseniz bu veri otomatik olarak silinecek.") },
            confirmButton = {
                TextButton(onClick = {
                    selectedBuilding?.let {
                        myBuildingsViewModel.deleteBuilding(it) // Veritabanından sil
                        myBuildingsViewModel.removeBuildingFromList(it) // Listeden sil
                    }
                    showDeleteConfirmation = false
                }) {
                    Text("Tamam")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Hayır")
                }
            }
        )
    }
}

/*
@Composable
fun BottomNavigationMyBuildingScreen(
    modifier: Modifier = Modifier,
    myBuildingsViewModel: BottomMyBuildingsViewModel = koinViewModel()
) {
    val userBuildings = myBuildingsViewModel.userBuildings.value

    LazyColumn(modifier = modifier) {
        items(userBuildings) { houseData ->
            val imageUris = houseData.photos.mapNotNull { url ->
                try {
                    Uri.parse(url)
                } catch (e: Exception) {
                    null // Eğer geçersiz bir URL varsa, Uri'ye çevirmeyi atla
                }
            }

            HouseCardHeartless(
                name = houseData.name ?: "",
                imageUrls = imageUris,
                topText = houseData.title ?: "",
                middleText = houseData.address ?: "",
                bottomText = houseData.price ?: "",
                onCardClick = {
                    // Kart tıklanıldığında yapılacak işlemler
                }
            )
        }
    }
}

class BottomMyBuildingsViewModel(
    private val myBuildingRepository: MyBuildingRepository
) : ViewModel() {
    private val _userBuildings = mutableStateOf<List<HouseData>>(emptyList())
    val userBuildings: State<List<HouseData>> = _userBuildings

    init {
        loadUserBuildings()
    }

    private fun loadUserBuildings() {
        viewModelScope.launch {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                try {
                    val buildings = myBuildingRepository.getUserBuildings(currentUser.uid)
                    _userBuildings.value = buildings
                    Log.d("BottomMyBuildingsViewModel", "Kullanıcı binaları yüklendi: ${buildings.size} bina bulundu")
                } catch (e: Exception) {
                    Log.e("BottomMyBuildingsViewModel", "Binalar yüklenirken hata oluştu", e)
                }
            } else {
                Log.d("BottomMyBuildingsViewModel", "Kullanıcı oturum açmamış")
            }
        }
    }
}


 */