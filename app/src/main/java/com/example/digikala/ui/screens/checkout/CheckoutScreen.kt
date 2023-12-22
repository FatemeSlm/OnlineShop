package com.example.digikala.ui.screens.checkout

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.UserAddress
import com.example.digikala.data.model.checkout.OrderRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.screens.cart.CartPriceDetail
import com.example.digikala.ui.screens.cart.CompleteThePurchase
import com.example.digikala.util.Constants.User_Token
import com.example.digikala.viewmodel.CartViewModel
import com.example.digikala.viewmodel.CheckoutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val cartDetail by cartViewModel.cartDetail.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    var shippingCost by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    var address: UserAddress? = null
    var addressName by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        checkoutViewModel.getShippingCost(addressName)
    }
    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false

        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CheckoutScreen shippingCost error : ${shippingCostResult.message}")

        }

        is NetworkResult.Loading -> {
            loading = true

        }
    }


    var orderId by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Dispatchers.Main) {
        checkoutViewModel.orderResponse.collectLatest { orderResult ->
            when (orderResult) {
                is NetworkResult.Success -> {
                    orderId = orderResult.data ?: ""
                    navController.navigate(
                        Screen.ConfirmPurchase.withArgs(
                            orderId,
                            cartDetail.payablePrice + shippingCost
                        )
                    )
                }

                is NetworkResult.Error -> {
                    Log.e("3636", "checkoutScreen order error : ${orderResult.message}")
                }

                is NetworkResult.Loading -> {}
            }
        }
    }


    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            it != ModalBottomSheetValue.HalfExpanded
        },
        skipHalfExpanded = false
    )


    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            DeliveryTimeBottomSheet()
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            LazyColumn {
                item { CheckoutTopBar(navController) }
                item {
                    CartAddressSection(navController) { addressList ->
                        if (addressList.isNotEmpty()) {
                            address = addressList[0]
                            addressName = addressList[0].address
                        }
                    }
                }
                item {
                    CartItemReviewSection(
                        cartDetail = cartDetail,
                        currentCartItem = cartItems
                    ) {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible) {
                                modalSheetState.hide()
                            } else {
                                modalSheetState.show()
                            }
                        }
                    }
                }
                item { CartInfoSection() }
                item { CartPriceDetail(cartDetail, shippingCost) }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                if (loading) {
                    Loading(height = 65.dp, isDark = true)
                } else {
                    CompleteThePurchase(
                        price = cartDetail.payablePrice,
                        shippingCost = shippingCost
                    ) {
                        if (address != null) {
                            checkoutViewModel.addNewOrder(
                                OrderRequest(
                                    orderAddress = address!!.address,
                                    orderProducts = cartItems,
                                    orderTotalDiscount = cartDetail.totalDiscount,
                                    orderTotalPrice = cartDetail.payablePrice + shippingCost,
                                    orderUserName = address!!.name,
                                    orderUserPhone = address!!.phone,
                                    token = User_Token
                                )
                            )
                        } else {
                            Toast.makeText(
                                context,
                                context.resources.getText(R.string.no_selected_address),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }

}