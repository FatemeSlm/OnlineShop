package com.example.digikala.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val extraSmall: Dp = 4.dp,
    val semiSmall: Dp = 6.dp,
    val small: Dp = 8.dp,
    val biggerSmall: Dp = 10.dp,
    val semiMedium: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val biggerMedium: Dp = 20.dp,
    val semiLarge: Dp = 24.dp,
    val large: Dp = 32.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing :Spacing
@Composable
@ReadOnlyComposable
get() = LocalSpacing.current


