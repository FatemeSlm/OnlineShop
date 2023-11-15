package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.home.AmazingProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.lightGreen
import com.example.digikala.util.Constants
import com.example.digikala.viewmodel.HomeViewModel

@Composable
fun SuperMarketAmazingOffer(
    viewModel: HomeViewModel = hiltViewModel()
) {
    var superMarketAmazingProductList by remember {
        mutableStateOf<List<AmazingProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val superMarketAmazingProductResult by viewModel.superMarketAmazingProducts.collectAsState()
    when (superMarketAmazingProductResult) {
        is NetworkResult.Success -> {
            superMarketAmazingProductList = superMarketAmazingProductResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e(
                "3636",
                "SuperMarketAmazingOffer error : ${superMarketAmazingProductResult.message}"
            )
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        Loading(height = 400.dp, isDark = true)
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.lightGreen)
        ) {
            LazyRow(
                modifier = Modifier.background(MaterialTheme.colorScheme.lightGreen)
            ) {
                item {
                    AmazingOfferCard(
                        topImageResId = amazingLogoChangeByLang(),
                        bottomImageResId = R.drawable.fresh
                    )
                }
                items(superMarketAmazingProductList) { item ->
                    AmazingProduct(item)
                }
                item {
                    AmazingShowMoreCard()
                }
            }

        }
    }
}

@Composable
private fun amazingLogoChangeByLang(): Int {
    return if (Constants.App_Language == Constants.English_Lang) {
        R.drawable.amazing_en
    } else {
        R.drawable.supermarketamazings
    }
}