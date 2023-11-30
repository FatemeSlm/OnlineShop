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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.ui.component.Loading
import com.example.digikala.util.Constants.User_Token
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun ShoppingCart(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {

    val cartDetail = viewModel.cartDetail.collectAsState()

    val currentCartResult by viewModel.currentCartItems.collectAsState()

    var isCartEmpty by remember {
        mutableStateOf(true)
    }

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

            item {
                if (User_Token.isEmpty()) {
                    LoginOrRegisterSection(navController)
                }
            }

            when (currentCartResult) {
                is CartState.Success -> {
                    val currentCardList = currentCartResult.data ?: emptyList()

                    if (currentCardList.isEmpty()) {
                        isCartEmpty = true
                        item { EmptyShoppingCart() }
                        item { SuggestedListSection() }
                    } else {
                        isCartEmpty = false
                        items(currentCardList) {
                            CartItemCard(item = it, cartStatus = CartStatus.CURRENT_CART)
                        }
                        item {
                            CartPriceDetail(cartDetail.value)
                        }
                    }
                }

                is CartState.Error -> {
                    Log.e("3636", "ShoppingCart ${currentCartResult.error}")
                }

                is CartState.Loading -> {
                    item {
                        Loading(
                            height = LocalConfiguration.current.screenHeightDp.dp - 60.dp,
                            isDark = true
                        )
                    }
                }
            }
        }

        if (!isCartEmpty) {
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