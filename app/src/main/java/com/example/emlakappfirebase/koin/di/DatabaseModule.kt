package com.example.emlakappfirebase.koin.di

import org.koin.dsl.module
import androidx.room.Room
import com.example.emlakappfirebase.viewmodels.BottomExploreViewModel
import com.example.emlakappfirebase.viewmodels.BottomFavouriteViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get

val databaseModule = module {
   /*
    single {
        Room.databaseBuilder(get(), FavouriteDatabase::class.java, "favourite_database")
            .build()
    }
    factory { get<FavouriteDatabase>().favouriteDao() }
    viewModel { BottomExploreViewModel(get(), get()) }
    viewModel { BottomFavouriteViewModel(get()) }
*/
}
