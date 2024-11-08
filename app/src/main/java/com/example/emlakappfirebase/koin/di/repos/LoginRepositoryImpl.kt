package com.example.emlakappfirebase.koin.di.repos

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginRepositoryImpl(private val firebaseAuth: FirebaseAuth) : LoginRepository {

    override fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    onResult(true, null)

                    //Kullanıcı Uidsi
                    Log.d("AuthCheck", "User authenticated with uid: ${user?.uid}")
                } else {
                    onResult(false, task.exception?.message)
                    Log.e("AuthCheck", "Authentication failed: ${task.exception?.message}")
                }
            }
    }
}