package com.example.emlakappfirebase.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeRepairService
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HomeRepairService
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SwapHorizontalCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.emlakappfirebase.R
import com.example.emlakappfirebase.ui.screens.navigation.BottomNavigationItem
import com.example.emlakappfirebase.ui.theme.Primary
import com.example.emlakappfirebase.utils.AppToolbar
import com.example.emlakappfirebase.utils.NavigationDrawerBody
import com.example.emlakappfirebase.utils.NavigationDrawerHeader
import com.example.emlakappfirebase.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    mainViewModel.getUserData()

    val items = listOf(
        BottomNavigationItem(
            title = "Kesfet",
            screenName = "explore_screen",
            selectedIcon = Icons.Filled.SwapHorizontalCircle,
            unselectedIcon = Icons.Outlined.SwapHorizontalCircle,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Favourites",
            screenName = "favourite_screen",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false,
           //,
            // badgeCount = 0
        ),
        BottomNavigationItem(
            title = "My Portfolio",
            screenName = "portfolio_screen",
            selectedIcon = Icons.Filled.Apartment,
            unselectedIcon = Icons.Outlined.Apartment,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Show On Map",
            screenName = "show_on_map_screen",
            selectedIcon = Icons.Filled.Map,
            unselectedIcon = Icons.Outlined.Map,
            hasNews = false
        )
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val toolbarTitle = when (selectedItemIndex) {
        0 -> "Explore"
        1 -> "Favourites"
        2 -> "My Buildings"
        3 -> "Maps"
        else -> ""
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(toolbarTitle = toolbarTitle,
                logoutButtonClicked = {
                    mainViewModel.logout()
                    navController.navigate("login_screen") {
                        navController.navigateUp()
                    }
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_screen") {
                        navController.navigateUp()
                    }
                },
                shape = RoundedCornerShape(50),
                backgroundColor = Primary
            ) {
                Icon(Icons.Filled.Add, tint = Color.White, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

        bottomBar = {
            BottomAppBar(
                cutoutShape = RoundedCornerShape(50),
                content = {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = { selectedItemIndex = index },
                                icon = {
                                    BadgedBox(badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else {
                                                item.unselectedIcon
                                            },
                                            contentDescription = item.title,
                                            tint = Primary
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            )
        },

        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawerHeader(mainViewModel.emailId.value)
            NavigationDrawerBody(navigationDrawerItems = mainViewModel.navigationItemsList,
                onNavigationItemClicked = {
                    Log.d("ComingHere", "inside_NavigationItemClicked")
                    Log.d("ComingHere", "${it.itemId} ${it.title}")
                })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ContentScreen(modifier = Modifier.padding(paddingValues), selectedItemIndex)
            }
        }
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int) {
    when (selectedIndex) {
        0 -> BottomNavigationExploreScreen()
        1 -> BottomNavigationFavouriteScreen()
        2 -> BottomNavigationMyBuildingScreen()
        3 -> BottomNavigationOnMapScreen()
    }
}




@Preview
@Composable
fun HomeScreenPreview() {
  //  MainScreen()
}

