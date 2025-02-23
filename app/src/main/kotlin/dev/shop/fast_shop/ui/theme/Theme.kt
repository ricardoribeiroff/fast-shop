package dev.shop.fast_shop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@Composable
fun FastShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
}

private val DarkColorScheme = darkColorScheme(
    primary = blue,
    secondary = black,
    tertiary = lightBeige
)

private val LightColorScheme = lightColorScheme(
//    primary = blue,
//    secondary = white,
//    tertiary = darkBeige


)


