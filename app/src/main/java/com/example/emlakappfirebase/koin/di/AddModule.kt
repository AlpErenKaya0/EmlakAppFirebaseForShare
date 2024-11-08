package com.example.emlakappfirebase.koin.di

//import com.example.emlakappfirebase.koin.di.repos.AddRepository
//import com.example.emlakappfirebase.koin.di.repos.AddRepositoryImpl
import com.example.emlakappfirebase.koin.di.repos.AddRepository
import com.example.emlakappfirebase.koin.di.repos.AddRepositoryImpl
import com.example.emlakappfirebase.viewmodels.AddViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val addModule = module {

    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { FirebaseAuth.getInstance() }


    single<AddRepository> {
        AddRepositoryImpl(
            get(), // Firestore
            get(),
            get()  // FirebaseAuth
        )
    }


    viewModel { AddViewModel(get()) }

}



