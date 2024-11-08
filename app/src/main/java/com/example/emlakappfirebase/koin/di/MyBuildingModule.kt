package com.example.emlakappfirebase.koin.di

import com.example.emlakappfirebase.koin.di.repos.LoginRepository
import com.example.emlakappfirebase.koin.di.repos.LoginRepositoryImpl
import com.example.emlakappfirebase.koin.di.repos.MyBuildingRepository
import com.example.emlakappfirebase.koin.di.repos.MyBuildingRepositoryImpl
import com.example.emlakappfirebase.viewmodels.BottomMyBuildingsViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myBuildingModule = module {
    single<MyBuildingRepository> { MyBuildingRepositoryImpl() }
    viewModel { BottomMyBuildingsViewModel(get()) }
}