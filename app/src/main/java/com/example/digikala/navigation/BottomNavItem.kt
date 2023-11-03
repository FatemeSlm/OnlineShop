package com.example.digikala.navigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: Painter,
    val unSelectedIcon: Painter
)