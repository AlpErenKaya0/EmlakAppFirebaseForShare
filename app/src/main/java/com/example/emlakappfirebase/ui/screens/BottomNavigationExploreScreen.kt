package com.example.emlakappfirebase.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.emlakappfirebase.utils.HouseCard
import com.example.emlakappfirebase.viewmodels.BottomExploreViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel
@Composable
fun BottomNavigationExploreScreen(
    modifier: Modifier = Modifier,
    exploreViewModel: BottomExploreViewModel = koinViewModel()
) {
    val houseDataList by exploreViewModel.houseDataList
    val isRefreshing by exploreViewModel.isRefreshing.collectAsState()

    LaunchedEffect(Unit) {
        exploreViewModel.refreshHouseData()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { exploreViewModel.refreshHouseData() },
        modifier = modifier
    ) {
        if (houseDataList.isEmpty()) {
            Text(text = "No data available", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        } else {
            LazyColumn {
                items(houseDataList) { houseData ->
                    val imageUris = houseData.photos.mapNotNull { url ->
                        try {
                            Uri.parse(url)
                        } catch (e: Exception) {
                            null
                        }
                    }

                    HouseCard(
                        name = houseData.name ?: "",
                        imageUrls = imageUris,
                        topText = houseData.title ?: "",
                        middleText = houseData.address ?: "",
                        bottomText = houseData.price ?: "",
                        onHeartClick = { isFilled ->
                            exploreViewModel.onHeartClick(houseData, isFilled)
                            Log.d("e","Kalp durumu: $isFilled")
                        },
                        onCardClick = {
                            println("Kart tıklandı!")
                        }
                    )
                }
            }
        }
    }
}

