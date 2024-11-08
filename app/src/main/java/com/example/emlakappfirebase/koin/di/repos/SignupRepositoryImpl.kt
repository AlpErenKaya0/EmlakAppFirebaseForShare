package com.example.emlakappfirebase.koin.di.repos

import com.google.firebase.auth.FirebaseAuth

class SignupRepositoryImpl(private val firebaseAuth: FirebaseAuth) : SignupRepository {

    override fun createUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.message)
            }
    }
}
