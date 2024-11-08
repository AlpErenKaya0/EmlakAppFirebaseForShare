package com.example.emlakappfirebase.koin.di.repos

interface LoginRepository {
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit)
}
