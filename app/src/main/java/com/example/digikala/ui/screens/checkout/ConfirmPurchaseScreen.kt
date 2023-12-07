package com.example.digikala.ui.screens.checkout

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.User_Token
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator
import com.example.digikala.util.ZarinpalPurchase
import com.example.digikala.viewmodel.CartViewModel
import com.example.digikala.viewmodel.CheckoutViewModel

@Composable
fun ConfirmPurchaseScreen(
    navController: NavHostController,
    orderId: String,
    orderPrice: String,
    cartViewModel: CartViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var orderStatus by remember {
        mutableStateOf(context.getString(R.string.waiting_for_purchase))
    }

    LaunchedEffect(true) {
        ZarinpalPurchase.fakePurchase(
            activity = context as Activity,
            amount = orderPrice.toLong(),
            description = "خرید تستی از دیجی کالا"
        ) { transactionId ->

            Log.e("3636", "Transaction Id : $transactionId")

            orderStatus = context.getString(R.string.purchase_is_ok)
            cartViewModel.deleteAllItems()

            checkoutViewModel.confirmPurchase(
                ConfirmPurchaseRequest(
                    token = User_Token,
                    transactionId = transactionId,
                    orderId = orderId
                )
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = digitByLocateAndSeparator(orderPrice),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = orderStatus,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_code),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = orderId,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Button(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.red),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {

            Text(
                text = stringResource(id = R.string.return_to_home_page),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )
        }

    }

}