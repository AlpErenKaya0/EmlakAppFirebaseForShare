package com.example.emlakappfirebase.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emlakappfirebase.ui.responsiveScreen.Orientation
import com.example.emlakappfirebase.ui.responsiveScreen.rememberWindowSizeClass
import com.example.emlakappfirebase.ui.screens.navigation.Navigation
import com.example.emlakappfirebase.ui.theme.AppTheme
import com.example.emlakappfirebase.ui.theme.EmlakAppFirebaseTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val window = rememberWindowSizeClass()
            EmlakAppFirebaseTheme(windowSizeClass = window) {
                Navigation()

            }
        }

    }
}

//KİŞİ FOTOĞRAFI VE İSMİ SONRANIN MESELESİ. ÖNCE ANA İŞLEVİ YAP!

//Parmak izi
//Responsive ekran
//Easy Permission
//Ekran arası TYPE SAFETY geçiş
// BELKİ dark theme
//background download Notification ile
//alt bar
//gece modu sabah modu
//onboarding ekranı
//yabancı dil eklentisi
//your session has been expired ekranı


