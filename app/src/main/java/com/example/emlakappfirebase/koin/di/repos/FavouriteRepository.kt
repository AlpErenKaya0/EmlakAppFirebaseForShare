package com.example.emlakappfirebase.koin.di.repos

import com.example.emlakappfirebase.ui.screens.logic.HouseData

interface FavouriteRepository {
    suspend fun getUserFavourites(userId: String): List<HouseData>
    suspend fun addToFavourites(houseData: HouseData, userId: String): Boolean
    suspend fun removeFromFavourites(houseDataId: String, userId: String): Boolean
}