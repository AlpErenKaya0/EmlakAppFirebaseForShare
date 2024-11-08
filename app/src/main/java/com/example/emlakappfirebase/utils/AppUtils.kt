package com.example.emlakappfirebase.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.example.emlakappfirebase.ui.responsiveScreen.Dimensions
import com.example.emlakappfirebase.ui.responsiveScreen.Orientation
import com.example.emlakappfirebase.ui.responsiveScreen.smallDimensions



@Composable
fun ProvideAppUtils(
    dimensions: Dimensions,
    orientation: Orientation,
    content: @Composable () ->Unit
) {
    val dimSet = remember{dimensions}
    val orientation = remember{orientation}
    CompositionLocalProvider(
        LocalAppDimens provides dimSet,
        LocalOrientationMode provides orientation,
        content = content
    )
}

val LocalAppDimens = compositionLocalOf {
    smallDimensions
}

val LocalOrientationMode = compositionLocalOf {
    Orientation.Portrait
}
//Burada mı alacağız dil ve darkTheme'e göre işlemleri