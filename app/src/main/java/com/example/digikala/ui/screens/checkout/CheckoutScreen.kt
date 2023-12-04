package com.example.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.screens.cart.CartPriceDetail
import com.example.digikala.ui.screens.cart.CompleteThePurchase
import com.example.digikala.viewmodel.CartViewModel
import com.example.digikala.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    val cartDetail by cartViewModel.cartDetail.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()


    var shippingCost by remember {
        mutableIntStateOf(0)
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false

        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CheckoutScreen error : ${shippingCostResult.message}")

        }

        is NetworkResult.Loading -> {
            loading = true

        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn {
            item { CheckoutTopBar(navController) }
            item {
                CartAddressSection(navController) { addressList ->
                    if (addressList.isNotEmpty()) {
                        checkoutViewModel.getShippingCost(addressList[0].address)
                    } else {
                        checkoutViewModel.getShippingCost("")
                    }
                }
            }
            item {
                CartItemReviewSection(
                    shippingCost = shippingCost,
                    cartDetail = cartDetail,
                    currentCartItem = cartItems
                )
            }
            item { CartInfoSection() }
            item { CartPriceDetail(cartDetail, shippingCost) }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {

            CompleteThePurchase(price = cartDetail.payablePrice, shippingCost = shippingCost) {

            }
        }

    }

}