//package com.example.emlakappfirebase.ui.screens.logic.data.local_db
/*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.emlakappfirebase.ui.screens.logic.HouseData

@Database(entities = [FavouriteData::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavouriteDatabase: RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

    //2. bir tablo kullanılıyorsa, buraya Dao'sunun eklenmesi yeterli olacaktır
    companion object {
        @Volatile
        private var INSTANCE: FavouriteDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: makeDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(context,
            FavouriteDatabase::class.java, "favourite_database")
            .addMigrations(MIGRATION_1_2)
            .build()

    }
}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Migration işlemleri
    }
}
 */