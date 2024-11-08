/*
package com.example.emlakappfirebase.ui.screens.logic.data.local_db

import androidx.lifecycle.LiveData

class FavouriteRepository(private val favouriteDao: FavouriteDao) {
    val readAllData: LiveData<List<FavouriteData>> = favouriteDao.readAllData()

    suspend fun addFavourite(favourite: FavouriteData) {
        favouriteDao.addFavorite(favourite)
    }

    suspend fun deleteFavourite(favourite: FavouriteData) {
        favouriteDao.deleteFavorite(favourite)
    }

    fun getFavouritesByName(name: String): LiveData<List<FavouriteData>> {
        return favouriteDao.getFavouritesByName(name)
    }

    fun getFavouritesBetween30And40(): LiveData<List<FavouriteData>> {
        return favouriteDao.getFavouritesBetween30And40()
    }

    suspend fun deleteAllFavourites() {
        favouriteDao.deleteAllFavourites()
    }
}
 */