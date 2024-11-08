/*
package com.example.emlakappfirebase.koin.di.repos

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class SessionRepositoryImpl(
    private val auth: FirebaseAuth
) : SessionRepository {

    private var sessionTimer: Job? = null
    private val sessionTimeoutMillis: Long = 50000 // 50 saniye
    private val isSessionActive = MutableStateFlow(true)

    override fun startSessionTimer(navController: NavController) {
        stopSessionTimer() // Önceki zamanlayıcıyı durdur
        isSessionActive.value = true

        sessionTimer = CoroutineScope(Dispatchers.Main).launch {
            delay(sessionTimeoutMillis)
            if (isSessionActive.value) {
                // Oturum süresi doldu, logout işlemi yap
                logoutAndNavigate(navController) // Logout ve yönlendirme işlemi
            }
        }
    }

    private fun logoutAndNavigate(navController: NavController) {
        auth.signOut() // Kullanıcıyı çıkart
        // Yönlendirme işlemi
        navController.navigate("login_screen") {
            popUpTo(0) { inclusive = true } // Tüm önceki sayfaları temizle
            launchSingleTop = true
        }
    }

    override fun resetSessionTimer(navController: NavController) {
        isSessionActive.value = true
        startSessionTimer(navController) // Zamanlayıcıyı yeniden başlat
    }

    override fun stopSessionTimer() {
        sessionTimer?.cancel() // Zamanlayıcıyı durdur
        sessionTimer = null
    }

    override fun isSessionExpired(): Boolean {
        return !isSessionActive.value
    }
}
*/
