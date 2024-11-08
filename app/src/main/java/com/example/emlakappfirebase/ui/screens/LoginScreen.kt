package com.example.emlakappfirebase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.emlakappfirebase.R
import com.example.emlakappfirebase.utils.ButtonComponent
import com.example.emlakappfirebase.utils.ClickableLoginTextComponent
import com.example.emlakappfirebase.utils.DividerTextComponent
import com.example.emlakappfirebase.utils.HeadingTextComponent
import com.example.emlakappfirebase.utils.MyTextFieldComponent
import com.example.emlakappfirebase.utils.NormalTextComponent
import com.example.emlakappfirebase.utils.PasswordTextFieldComponent
import com.example.emlakappfirebase.utils.UnderLinedTextComponent
import com.example.emlakappfirebase.viewmodels.events.LoginUIEvent
import com.example.emlakappfirebase.viewmodels.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen( loginViewModel: LoginViewModel = koinViewModel(), navController:NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate("sign_up_screen"){
                        navController.navigateUp()

                    }
                })
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
            navController.navigate("main_screen") {
                navController.navigateUp()
            }
        }
    }

/*
    SystemBackButtonHandler {
       // Navigation.navigateTo(Screen.SignUpScreen)
    }
    */
}

@Preview
@Composable
fun LoginScreenPreview() {
    //LoginScreen()
}
// For this design: https://github.com/droid-lover/AppsUsingJetpackCompose