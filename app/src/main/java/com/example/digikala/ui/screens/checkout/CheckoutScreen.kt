package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CheckoutScreen(
    navController: NavHostController
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn {
            item { CheckoutTopBar(navController) }
            item { CartAddressSection(navController) }
        }
    }

}