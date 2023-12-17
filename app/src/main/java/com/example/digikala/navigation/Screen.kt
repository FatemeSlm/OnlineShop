package com.example.digikala.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen(route = "splash_screen")
    data object Home : Screen(route = "home_screen")
    data object Category : Screen(route = "category_screen")
    data object Cart : Screen(route = "cart_screen")
    data object Profile : Screen(route = "profile_screen")
    data object WebView : Screen(route = "webView_screen")
    data object Checkout : Screen(route = "checkout_screen")
    data object ConfirmPurchase : Screen(route = "confirm_purchase_screen")
    data object ItemDetail : Screen(route = "item_detail_screen")
    data object ItemDescription : Screen(route = "item_description_screen")
    data object ItemTechFeature : Screen(route = "item_tech_feature_screen")
    data object NewComment : Screen(route = "new_comment_screen")
    data object AllComment : Screen(route = "all_comment_screen")


    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}