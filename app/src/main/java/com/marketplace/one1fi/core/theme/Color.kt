package com.marketplace.one1fi.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

/**
 * Palette lifted from the existing 1Fi app UI (home / shop screens).
 * If the host app already exposes these as design tokens, replace this
 * file with a reference to that token set instead of duplicating values.
 */

// Brand purple
val OneFiPurple = Color(0xFF6D28D9)
val OneFiPurpleDark = Color(0xFF4C1D95)
val OneFiPurpleLight = Color(0xFF9333EA)

// Accent
val OneFiGreen = Color(0xFF16A34A)
val OneFiGreenLight = Color(0xFFDCFCE7)
val OneFiAmber = Color(0xFFF59E0B)

// Neutrals
val OneFiBackground = Color(0xFFF5F4FA)
val OneFiSurface = Color(0xFFFFFFFF)
val OneFiBorder = Color(0xFFE7E4F0)
val OneFiTextPrimary = Color(0xFF1A1523)
val OneFiTextSecondary = Color(0xFF6E6B7A)
val OneFiTextOnPurple = Color(0xFFFFFFFF)

val OneFiHeroGradient = Brush.linearGradient(
    colors = listOf(OneFiPurpleDark, OneFiPurple, OneFiPurpleLight)
)
