package com.example.emlakappfirebase.koin.di.repos

import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MyBuildingRepositoryImpl : MyBuildingRepository {
    private val db = FirebaseFirestore.getInstance()

    override suspend fun getUserBuildings(userId: String): List<HouseData> {
        val userBuildings = mutableListOf<HouseData>()

        val querySnapshot = db.collection("posts")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        for (document in querySnapshot.documents) {
            val houseData = document.toObject(HouseData::class.java)
            houseData?.let { userBuildings.add(it) }
        }

        return userBuildings
    }
    override suspend fun deleteBuilding(houseData: HouseData) {
        val querySnapshot = db.collection("posts")
            .whereEqualTo("userId", houseData.userId) // userId ile eşleşen belgeleri bul
            .whereEqualTo("address", houseData.address)
            .whereEqualTo("isPrivate", houseData.isPrivate)
            .whereEqualTo("name", houseData.name)
            .whereEqualTo("price", houseData.price)
            .whereEqualTo("title", houseData.title)
            .get()
            .await()

        for (document in querySnapshot.documents) {
            // Eşleşen her belgeyi sil
            document.reference.delete().await()
        }
    }
}
