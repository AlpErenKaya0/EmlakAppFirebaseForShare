package com.example.emlakappfirebase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emlakappfirebase.R
import com.example.emlakappfirebase.utils.BackButton
//import com.example.emlakappfirebase.ui.screens.navigation.SystemBackButtonHandler
import com.example.emlakappfirebase.utils.HeadingTextComponent


@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {

       // HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
        Box(modifier = Modifier.fillMaxSize()) {
            BackButton(
                modifier = Modifier.align(Alignment.TopStart), // Ekranın üst soluna hizalama
                onBackPressed = {
                    navController.popBackStack()
                },
                value =  stringResource(id =R.string.terms_and_conditions_header)
            )
            // Diğer içerikler
        }

    }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
   // TermsAndConditionsScreen()
}
// Burada Deeplinking veya Link kullanılabilir.