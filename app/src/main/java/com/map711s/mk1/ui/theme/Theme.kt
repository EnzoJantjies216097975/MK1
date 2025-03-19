package com.map711s.mk1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40

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
// Dark color scheme for the app (we'll primarily use this)
private val DarkColorScheme = darkColorScheme(
    primary = MKRed,
    secondary = MKGold,
    tertiary = Color.White,
    background = MKBlack,
    surface = MKDarkGray,
    onPrimary = Color.White,
    onSecondary = MKBlack,
    onTertiary = MKBlack,
    onBackground = Color.White,
    onSurface = Color.White
)

// Light color scheme (as a fallback)
private val LightColorScheme = lightColorScheme(
    primary = MKRed,
    secondary = MKGold,
    tertiary = MKDarkGray,
    background = Color.White,
    surface = Color.LightGray,
    onPrimary = Color.White,
    onSecondary = MKBlack,
    onTertiary = Color.White,
    onBackground = MKBlack,
    onSurface = MKBlack
)

@Composable
fun MK1Theme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}