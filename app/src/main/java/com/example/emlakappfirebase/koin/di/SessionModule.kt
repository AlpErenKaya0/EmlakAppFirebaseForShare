/*
package com.example.emlakappfirebase.koin.di

import com.example.emlakappfirebase.koin.di.repos.LoginRepository
import com.example.emlakappfirebase.koin.di.repos.LoginRepositoryImpl
import com.example.emlakappfirebase.koin.di.repos.SessionRepository
import com.example.emlakappfirebase.koin.di.repos.SessionRepositoryImpl
import com.example.emlakappfirebase.viewmodels.LoginViewModel
import com.example.emlakappfirebase.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sessionModule = module {
    single<SessionRepository> { SessionRepositoryImpl(get()) }
viewModel{MainViewModel(get()) }
}

 */