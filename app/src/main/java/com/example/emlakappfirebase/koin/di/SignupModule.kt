package com.example.emlakappfirebase.koin.di

import com.example.emlakappfirebase.koin.di.repos.SignupRepository
import com.example.emlakappfirebase.koin.di.repos.SignupRepositoryImpl
import com.example.emlakappfirebase.viewmodels.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.google.firebase.auth.FirebaseAuth

val signupModule = module {

    // Provide FirebaseAuth instance
    single { FirebaseAuth.getInstance() }

    // Provide SignupRepository instance
    single<SignupRepository> { SignupRepositoryImpl(get()) }

    // Provide SignupViewModel instance
    viewModel { SignupViewModel(get()) }
}
