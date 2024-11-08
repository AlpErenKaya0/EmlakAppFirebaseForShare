package com.example.emlakappfirebase.koin.di.repos

interface SignupRepository {
    fun createUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)
}
