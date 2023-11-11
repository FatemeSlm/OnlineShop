package com.example.digikala.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.Red
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatusBarColor(
    navController: NavHostController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()

    val statusBarColor = if (isSystemInDarkTheme()) {
        Color.Black
    }else {
        Color.White
    }

    when (backStackEntry?.destination?.route) {
        Screen.Splash.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Red
                )
            }
        }

        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = statusBarColor
                )
            }
        }
    }
}