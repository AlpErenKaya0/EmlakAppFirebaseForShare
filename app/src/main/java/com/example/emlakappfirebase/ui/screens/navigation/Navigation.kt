package com.example.emlakappfirebase.ui.screens.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emlakappfirebase.ui.screens.AddScreen
import com.example.emlakappfirebase.ui.screens.BottomNavigationExploreScreen
import com.example.emlakappfirebase.ui.screens.DummyScreen
import com.example.emlakappfirebase.ui.screens.LoginScreen
import com.example.emlakappfirebase.ui.screens.MainScreen
import com.example.emlakappfirebase.ui.screens.MapScreen
import com.example.emlakappfirebase.ui.screens.SignUpScreen
import com.example.emlakappfirebase.ui.screens.TermsAndConditionsScreen
import com.example.emlakappfirebase.viewmodels.SignupViewModel

//Navigationlara viewmodellar eklenecek mi bilmiyorum gerek var mı

//https://www.youtube.com/watch?v=4gUeyNkGE3g

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = "sign_up_screen") {
            SignUpScreen(navController = navController)
        }
        composable(route = "terms_conditions_screen") {
            TermsAndConditionsScreen(navController = navController)
        }
        composable(route = "main_screen") {
            MainScreen(navController = navController)
        }
        composable(route = "add_screen") {
            AddScreen(navController = navController)
        }
        composable(route = "map_screen") {
            MapScreen(navController = navController)
        }
        composable(route = "dummy_screen") {
            DummyScreen(navController = navController)
        }
//        composable(route="explore_screen"){
//            BottomNavigationExploreScreen(navController= navController)
//        }
//        composable(route="detailed_item_screen"){
//            DetailedItemScreen(navController = navController)
//        }
    }
}



/* TypeSafe Navigation =
     val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenA
                ) {
                    composable<ScreenA> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                navController.navigate(ScreenB(
                                    name = null,
                                    age = 25
                                ))
                            }) {
                                Text(text = "Go to screen B")
                            }
                        }
                    }
                    composable<ScreenB> {
                        val args = it.toRoute<ScreenB>()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "${args.name}, ${args.age} years old")
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)
 */