package com.example.emlakappfirebase.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.emlakappfirebase.ui.responsiveScreen.Dimensions
import com.example.emlakappfirebase.ui.responsiveScreen.Orientation
import com.example.emlakappfirebase.ui.responsiveScreen.WindowSize
import com.example.emlakappfirebase.ui.responsiveScreen.WindowSizeClass
import com.example.emlakappfirebase.ui.responsiveScreen.compactDimensions
import com.example.emlakappfirebase.ui.responsiveScreen.largeDimensions
import com.example.emlakappfirebase.ui.responsiveScreen.mediumDimensions
import com.example.emlakappfirebase.ui.responsiveScreen.smallDimensions
import com.example.emlakappfirebase.utils.LocalAppDimens
import com.example.emlakappfirebase.utils.LocalOrientationMode
import com.example.emlakappfirebase.utils.ProvideAppUtils

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80

)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun EmlakAppFirebaseTheme(
    windowSizeClass: WindowSizeClass,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val orientation = when{
       windowSizeClass.width.size > windowSizeClass.height.size -> Orientation.Landscape
       else -> Orientation.Portrait
    }
    val sizeThatMatters = when(orientation){
        Orientation.Portrait -> windowSizeClass.width
        Orientation.Landscape -> windowSizeClass.height
    }
    val dimensions = when(sizeThatMatters){
        is WindowSize.Small -> smallDimensions
        is WindowSize.Compact -> compactDimensions
        is WindowSize.Medium -> mediumDimensions
        else -> largeDimensions
    }


    val typography = when(sizeThatMatters){
        is WindowSize.Small -> TypographySmall
        is WindowSize.Compact -> TypographyCompact
        is WindowSize.Medium -> TypographyMedium
        else -> TypographyBig
    }

    ProvideAppUtils(dimensions = dimensions, orientation = orientation) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object AppTheme{
    val dimens:Dimensions
        @Composable
        get() = LocalAppDimens.current

    val orientation:Orientation
        @Composable
        get() = LocalOrientationMode.current
}