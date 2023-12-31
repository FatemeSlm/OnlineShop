package com.example.digikala.ui.screens.cart

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.ui.component.Loading
import com.example.digikala.util.Constants
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun NextShoppingList(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {

    val nextCartResult by viewModel.nextCartItems.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp)
    ) {

        item {
            if (Constants.User_Token.isEmpty()) {
                LoginOrRegisterSection(navController)
            }
        }

        when (nextCartResult) {
            is CartState.Success -> {
                val nextCartList = nextCartResult.data ?: emptyList()
                if (nextCartList.isEmpty()) {
                    item { EmptyNextShoppingList() }
                } else {
                    items(nextCartList) {
                        CartItemCard(item = it, cartStatus = CartStatus.NEXT_CART)
                    }
                }
            }

            is CartState.Error -> {
                Log.e("3636", "NextShoppingList ${nextCartResult.error}")
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
}