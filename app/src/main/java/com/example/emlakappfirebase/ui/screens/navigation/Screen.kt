package com.example.emlakappfirebase.ui.screens.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route:String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object TermsConditionsScreen: Screen("terms_conditions_screen")
    object MainScreen:Screen("main_screen")
    object AddScreen:Screen("add_screen")
    object MapScreen:Screen("map_screen")
    object DummyScreen:Screen("dummy_screen")
    object BottomNavigationExploreScreen:Screen("explore_screen")
//    object DetailedItemScreen:Screen("detailed_item_screen")
    // Burada yapılacak şeyden emin değilim,
    //alttaki BOTTOMNAVİGATİON için ekranları eklersek
    // bottomNav olmayan ekranlar olmuş olacak
    //gidilen ekranlarda
}
