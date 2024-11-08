package com.example.emlakappfirebase.koin.di.repos

import com.example.emlakappfirebase.ui.screens.logic.HouseData

interface MyBuildingRepository {
    suspend fun getUserBuildings(userId: String): List<HouseData>
    suspend fun deleteBuilding(houseData: HouseData)

}