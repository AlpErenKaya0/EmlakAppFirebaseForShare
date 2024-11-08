/*package com.example.emlakappfirebase.ui.screens.logic.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(houseData: FavouriteData)

    @Delete
    suspend fun deleteFavorite(houseData: FavouriteData)

    @Query("SELECT * FROM favorites")
    fun readAllData(): LiveData<List<FavouriteData>>

    @Query("SELECT * FROM favorites WHERE name LIKE :name")
    fun getFavouritesByName(name: String): LiveData<List<FavouriteData>>

    @Query("SELECT * FROM favorites WHERE price BETWEEN 30 AND 40")
    fun getFavouritesBetween30And40(): LiveData<List<FavouriteData>>

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavourites()
}


 */