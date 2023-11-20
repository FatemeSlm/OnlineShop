package com.example.digikala.ui.screens.cart

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.ui.component.Loading
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun ShoppingCart(
    viewModel: CartViewModel = hiltViewModel()
) {

    val cartDetail = viewModel.cartDetail.collectAsState()

    val currentCartResult by viewModel.currentCartItems.collectAsState()
    when (currentCartResult) {
        is CartState.Success -> {
            val currentCardList = currentCartResult.data ?: emptyList()

            if (currentCardList.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 60.dp)
                ) {
                    item { EmptyShoppingCart() }
                    item { SuggestedListSection() }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 60.dp)
                    ) {
                        items(currentCardList) {
                            CartItemCard(item = it, cartStatus = CartStatus.CURRENT_CART)
                        }
                        item {
                            CartPriceDetail(cartDetail.value)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 60.dp),

                        ) {
                        CompleteThePurchase(cartDetail.value.payablePrice)
                    }
                }
            }
        }

        is CartState.Error -> {
            Log.e("3636", "ShoppingCart ${currentCartResult.error}")
        }

        is CartState.Loading -> {
            Loading(height = LocalConfiguration.current.screenHeightDp.dp - 60.dp, isDark = true)
        }
    }
}