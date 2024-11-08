package com.example.emlakappfirebase.ui.screens

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MapScreen(navController: NavController,modifier: Modifier = Modifier) {
    Button(onClick = { navController.navigate("add_screen"){
        navController.navigateUp()

    } }) {

    }
}