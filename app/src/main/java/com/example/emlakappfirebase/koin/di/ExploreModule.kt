package com.example.emlakappfirebase.koin.di

import com.example.emlakappfirebase.koin.di.repos.AddRepository
import com.example.emlakappfirebase.koin.di.repos.AddRepositoryImpl
import com.example.emlakappfirebase.koin.di.repos.ExploreRepository
import com.example.emlakappfirebase.koin.di.repos.ExploreRepositoryImpl
import com.example.emlakappfirebase.viewmodels.AddViewModel
import com.example.emlakappfirebase.viewmodels.BottomExploreViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val exploreModule = module {
    // Firebase Firestore bağımlılığını Koin ile sağlıyoruz
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { FirebaseAuth.getInstance() }

    single<ExploreRepository> {
        ExploreRepositoryImpl(
            get(),
            get(),
            get()
        )
    }


    viewModel {   BottomExploreViewModel(get()) }
}
