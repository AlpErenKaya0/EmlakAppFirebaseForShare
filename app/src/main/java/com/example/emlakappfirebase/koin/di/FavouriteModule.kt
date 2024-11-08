package com.example.emlakappfirebase.koin.di

import com.example.emlakappfirebase.koin.di.repos.FavouriteRepository
import com.example.emlakappfirebase.koin.di.repos.FavouriteRepositoryImpl
import com.example.emlakappfirebase.viewmodels.BottomFavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    single<FavouriteRepository> { FavouriteRepositoryImpl() }
    viewModel { BottomFavouriteViewModel(get()) }
}
