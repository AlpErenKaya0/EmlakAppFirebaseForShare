package com.example.emlakappfirebase.koin.di.repos

import android.util.Log
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FavouriteRepositoryImpl : FavouriteRepository {

    private val db = FirebaseFirestore.getInstance()

    override suspend fun getUserFavourites(userId: String): List<HouseData> {
        val favourites = mutableListOf<HouseData>()

        val querySnapshot = db.collection("usersfavourites")
            .whereEqualTo("clickerUserId", userId)
            .get()
            .await() // Firebase için Kotlin Coroutines kullanıyorsanız await() ile bekleyin

        for (document in querySnapshot.documents) {
            val houseData = document.toObject(HouseData::class.java)
            houseData?.let { favourites.add(it) }
        }

        return favourites
    }

    override suspend fun addToFavourites(houseData: HouseData, userId: String): Boolean {
        return try {
            val favoritePost = hashMapOf(
                "clickerUserId" to userId,
                "name" to houseData.name,
                "title" to houseData.title,
                "address" to houseData.address,
                "price" to houseData.price,
                "photos" to houseData.photos
            )

            db.collection("usersfavourites").add(favoritePost).await() // Firestore'un otomatik ID üretmesine izin veriyoruz
            true // Başarı durumu
        } catch (e: Exception) {
            Log.e("FavouriteRepository", "Favorilere eklerken hata oluştu", e)
            false // Hata durumu
        }
    }

    override suspend fun removeFromFavourites(houseDataId: String, userId: String): Boolean {
        return try {
            // Burada, belirtilen kullanıcının favori kaydını silmek için
            val querySnapshot = db.collection("usersfavourites")
                .whereEqualTo("clickerUserId", userId)
                .whereEqualTo("houseDataId", houseDataId) // houseDataId'yı burada kullanmalısın

            val documents = querySnapshot.get().await()
            for (document in documents) {
                document.reference.delete()
            }
            true // Başarı durumu
        } catch (e: Exception) {
            Log.e("FavouriteRepository", "Favorilerden silerken hata oluştu", e)
            false // Hata durumu
        }
    }
}
