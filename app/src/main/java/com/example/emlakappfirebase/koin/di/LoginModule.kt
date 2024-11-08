package com.example.emlakappfirebase.koin.di

import com.example.emlakappfirebase.koin.di.repos.LoginRepository
import com.example.emlakappfirebase.koin.di.repos.LoginRepositoryImpl
import com.example.emlakappfirebase.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.google.firebase.auth.FirebaseAuth

val loginModule = module {

    // Provide FirebaseAuth instance
    single { FirebaseAuth.getInstance() }

    // Provide LoginRepository instance
    single<LoginRepository> { LoginRepositoryImpl(get()) }

    // Provide LoginViewModel instance
    viewModel { LoginViewModel(get()) }
}
