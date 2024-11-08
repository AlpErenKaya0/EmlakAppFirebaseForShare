package com.example.emlakappfirebase.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DummyScreen(navController: NavController,modifier: Modifier = Modifier) {
    Button(modifier = Modifier.padding(100.dp),onClick = {


        navController.navigate("add_screen"){
            navController.navigateUp()
        }

    }) {

    }
}