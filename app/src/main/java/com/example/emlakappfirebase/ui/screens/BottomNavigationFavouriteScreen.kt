package com.example.emlakappfirebase.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.emlakappfirebase.utils.HouseCard
import com.example.emlakappfirebase.viewmodels.BottomFavouriteViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel


@Composable
fun BottomNavigationFavouriteScreen(
    modifier: Modifier = Modifier,
    favouriteViewModel: BottomFavouriteViewModel = koinViewModel()
) {
    val favouriteHouseDataList by favouriteViewModel.favouriteHouseDataList
    val isRefreshing by favouriteViewModel.isRefreshing.collectAsState()

    // Sayfa her açıldığında refresh yap
    LaunchedEffect(Unit) {
        favouriteViewModel.refreshFavourites()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { favouriteViewModel.refreshFavourites() },
        modifier = modifier
    ) {
        if (favouriteHouseDataList.isEmpty()) {
            Text(text = "No data available", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        } else {
            LazyColumn {
                items(favouriteHouseDataList) { houseData ->
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

                        },
                        onCardClick = {

                        }
                    )
                }
            }
        }
    }
}
