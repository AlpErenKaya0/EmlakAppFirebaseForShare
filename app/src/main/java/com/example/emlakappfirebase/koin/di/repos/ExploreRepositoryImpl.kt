
package com.example.emlakappfirebase.koin.di.repos

import android.net.Uri
import android.util.Log
import com.example.emlakappfirebase.ui.screens.logic.HouseData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class ExploreRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private var firebaseAuth: FirebaseAuth,
    private var storage: FirebaseStorage
) : ExploreRepository {

    override suspend fun loadPublicData(): List<HouseData> {
        val houseList = mutableListOf<HouseData>()
        val postsRef = firestore.collection("posts").whereEqualTo("isPrivate", false)

        try {
            val postsSnapshot = postsRef.get().await()
            for (postDocument in postsSnapshot.documents) {
                val houseData = postDocument.toObject(HouseData::class.java)
                if (houseData != null) {
                    houseList.add(houseData)
                }
            }
        } catch (e: Exception) {
            println("Error loading data: ${e.localizedMessage}")
        }

        return houseList
    }

    // Kalp tıklama işlemi
    override suspend fun onHeartClick(houseData: HouseData, isFilled: Boolean) {
        val currentUser = firebaseAuth.currentUser
        if (isFilled && currentUser != null) {
            saveToFavorites(houseData, currentUser.uid)
        } else {
            println("Kullanıcı oturum açmamış ya da kalp boş!")
        }
    }

    // Favorilere kaydetme işlemi
    private fun saveToFavorites(houseData: HouseData, userId: String) {
        val favoritePost = hashMapOf(
            "clickerUserId" to userId,
            "name" to houseData.name,
            "title" to houseData.title,
            "address" to houseData.address,
            "price" to houseData.price,
            "photos" to houseData.photos
        )

        firestore.collection("usersfavourites")
            .add(favoritePost)
            .addOnSuccessListener {
                println("Post favorilere kaydedildi!")
            }
            .addOnFailureListener { e ->
                println("Favorilere kaydetme başarısız: ${e.localizedMessage}")
            }
    }
}

/*
        // Belirli bir yol üzerinde verileri al
        val publicDocRef = firestore.collection("posts")
            .document("public")
            .collection("9tEXMg7IQsNc0S4yKw0WGyl2Y9n2")
            .document("EcLmLaiJ614zRmZ1iTzn")

        try {
            // Dokümanı al
            val documentSnapshot = publicDocRef.get().await()
            if (documentSnapshot.exists()) {
                // Verileri almak
                val houseData = documentSnapshot.toObject(HouseData::class.java)
                if (houseData != null) {
                    houseList.add(houseData) // Listeye ekle
                }
            }
        } catch (e: Exception) {
            println("Error loading data: ${e.localizedMessage}")
        }
*/

