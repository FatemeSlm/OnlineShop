package com.example.digikala.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen(route = "splash_screen")
    data object Home : Screen(route = "home_screen")
    data object Category : Screen(route = "category_screen")
    data object Cart : Screen(route = "cart_screen")
    data object Profile : Screen(route = "profile_screen")


    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}