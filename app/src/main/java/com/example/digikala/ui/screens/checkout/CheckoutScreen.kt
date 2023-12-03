package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.ui.screens.cart.CartPriceDetail
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val cartDetail by cartViewModel.cartDetail.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn {
            item { CheckoutTopBar(navController) }
            item { CartAddressSection(navController) }
            item {
                CartItemReviewSection(
                    shippingCost = "2000",
                    cartDetail = cartDetail,
                    currentCartItem = cartItems
                )
            }
            item { CartInfoSection() }
            item { CartPriceDetail(cartDetail) }
        }
    }

}