package dev.shop.fast_shop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun FastShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
}val LightColorScheme = lightColorScheme(
    // Background & Surface
    primary = Color.Black,
    background = Color(0xFFEFEBEE),            // Fundo branco


)

// --------------------------
// Tema ESCURO – Paleta Azul
// --------------------------
val DarkColorScheme = darkColorScheme(
//    // Primary
//    primary = Color(0xFF1565C0),               // Azul claro para destacar no fundo escuro
//    onPrimary = Color(0xFF002171),             // Texto azul bem escuro
//    primaryContainer = Color(0xFF0D47A1),      // Container com azul intenso
//    onPrimaryContainer = Color(0xFFBBDEFB),    // Texto claro no container
//    inversePrimary = Color(0xFF1565C0),          // Azul intenso para inversão
//
//    // Secondary
//    secondary = Color(0xFF4FC3F7),             // Azul suave
//    onSecondary = Color(0xFF003C8F),           // Texto bem escuro
//    secondaryContainer = Color(0xFF01579B),      // Container com azul intenso
//    onSecondaryContainer = Color(0xFFB3E5FC),    // Texto claro
//
//    // Tertiary
//    tertiary = Color(0xFF81D4FA),              // Azul claro
//    onTertiary = Color(0xFF002F6C),            // Texto azul escuro
//    tertiaryContainer = Color(0xFF01579B),     // Container em azul intenso
//    onTertiaryContainer = Color(0xFFB3E5FC),   // Texto claro para contraste
//
//    // Background & Surface
//    background = Color(0xFF121212),            // Fundo escuro padrão
//    onBackground = Color(0xFFE0E0E0),          // Texto claro
//    surface = Color(0xFF121212),
//    onSurface = Color(0xFFE0E0E0),
//    surfaceVariant = Color(0xFF1E1E1E),        // Variante levemente mais clara
//    onSurfaceVariant = Color(0xFFCCCCCC),
//    surfaceTint = Color(0xFF82B1FF),
//
//    // Inverse
//    inverseSurface = Color(0xFFE0E0E0),        // Superfície clara para itens invertidos
//    inverseOnSurface = Color(0xFF121212),
//
//    // Error
//    error = Color(0xFFCF6679),
//    onError = Color(0xFF000000),
//    errorContainer = Color(0xFF8C1D18),
//    onErrorContainer = Color(0xFFFFDAD6),
//
//    // Outras propriedades
//    outline = Color(0xFF8A8A8A),
//    outlineVariant = Color(0xFF666666),
//    scrim = Color(0xFF000000),
//
//    // Extras
//    surfaceBright = Color(0xFF1E1E1E),
//    surfaceContainer = Color(0xFF121212),
//    surfaceContainerHigh = Color(0xFF1E1E1E),
//    surfaceContainerHighest = Color(0xFF2C2C2C),
//    surfaceContainerLow = Color(0xFF101010),
//    surfaceContainerLowest = Color(0xFF0D0D0D),
//    surfaceDim = Color(0xFF101010)
)