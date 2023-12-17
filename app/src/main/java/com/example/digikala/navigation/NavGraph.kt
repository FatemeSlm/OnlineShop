package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikala.ui.screens.cart.CartScreen
import com.example.digikala.ui.screens.category.CategoryScreen
import com.example.digikala.ui.screens.checkout.CheckoutScreen
import com.example.digikala.ui.screens.checkout.ConfirmPurchaseScreen
import com.example.digikala.ui.screens.home.HomeScreen
import com.example.digikala.ui.screens.home.WebPageScreen
import com.example.digikala.ui.screens.item_detail.AllCommentScreen
import com.example.digikala.ui.screens.item_detail.ItemDescriptionScreen
import com.example.digikala.ui.screens.item_detail.ItemDetailScreen
import com.example.digikala.ui.screens.item_detail.ItemTechFeatureScreen
import com.example.digikala.ui.screens.item_detail.NewCommentScreen
import com.example.digikala.ui.screens.profile.ProfileScreen
import com.example.digikala.ui.screens.splash.SplashScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = Screen.Cart.route) {
            CartScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let { myUrl ->
                WebPageScreen(navController = navController, url = myUrl)
            }
        }

        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }

        composable(route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("orderPrice") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            it.arguments?.getString("orderId")?.let { orderId ->
                it.arguments?.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }
        }

        composable(
            route = Screen.ItemDetail.route + "/{itemId}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
            )
        ) {
            it.arguments?.getString("itemId")?.let { itemId ->
                ItemDetailScreen(
                    navController = navController,
                    itemId = itemId
                )
            }
        }

        composable(
            route = Screen.ItemDescription.route + "/{desc}",
            arguments = listOf(
                navArgument("desc") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
            )
        ) {
            it.arguments?.getString("desc")?.let { desc ->
                ItemDescriptionScreen(
                    navController = navController,
                    description = desc
                )
            }
        }

        composable(
            route = Screen.ItemTechFeature.route + "/{features}",
            arguments = listOf(
                navArgument("features") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
            )
        ) {
            it.arguments?.getString("features")?.let { features ->
                ItemTechFeatureScreen(
                    navController = navController,
                    jsonString = features
                )
            }
        }

        composable(route = Screen.NewComment.route + "?itemId={itemId}?itemName={itemName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("itemName") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            it.arguments?.getString("itemId")?.let { itemId ->
                it.arguments?.getString("itemName")?.let { itemName ->
                    it.arguments?.getString("imageUrl")?.let { imageUrl ->
                        NewCommentScreen(
                            navController,
                            itemId,
                            itemName,
                            imageUrl
                        )
                    }
                }
            }
        }

        composable(
            route = Screen.AllComment.route + "/{itemId}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
            )
        ) {
            it.arguments?.getString("itemId")?.let { itemId ->
                AllCommentScreen(
                    navController = navController,
                    itemId = itemId
                )
            }
        }
    }
}