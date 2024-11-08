package com.example.emlakappfirebase.ui.screens.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
val title: String,
val screenName:String,
val selectedIcon: ImageVector,
val unselectedIcon: ImageVector,
val hasNews:Boolean,
val badgeCount:Int?=null
)
