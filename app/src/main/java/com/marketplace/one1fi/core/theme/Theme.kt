package com.marketplace.one1fi.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = OneFiPurple,
    onPrimary = OneFiTextOnPurple,
    secondary = OneFiGreen,
    background = OneFiBackground,
    surface = OneFiSurface,
    onBackground = OneFiTextPrimary,
    onSurface = OneFiTextPrimary,
    outline = OneFiBorder,
    error = Color(0xFFDC2626)
)

private val DarkColors = darkColorScheme(
    primary = OneFiPurpleLight,
    onPrimary = OneFiTextOnPurple,
    secondary = OneFiGreen,
    background = Color(0xFF121016),
    surface = Color(0xFF1C1A22),
    onBackground = Color(0xFFF2F1F5),
    onSurface = Color(0xFFF2F1F5),
    outline = Color(0xFF3A3742)
)

/**
 * Wraps the Marketplace feature (and any screen you render standalone
 * while developing it) with the same look as the rest of the 1Fi app.
 * In the real codebase this should simply be replaced by the app's
 * existing `OneFiTheme` / `AppTheme` composable — do not ship two themes.
 */
@Composable
fun OneFiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = OneFiTypography,
        shapes = OneFiShapes,
        content = content
    )
}
