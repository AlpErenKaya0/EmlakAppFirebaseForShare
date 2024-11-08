package com.example.emlakappfirebase.koin.di.repos

import android.net.Uri
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import kotlinx.coroutines.flow.Flow

interface ExploreRepository {
    suspend fun loadPublicData(): List<HouseData>


    suspend fun onHeartClick(houseData: HouseData, isFilled: Boolean)
}
