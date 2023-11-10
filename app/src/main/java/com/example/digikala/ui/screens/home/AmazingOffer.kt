package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.home.AmazingProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.lightRed
import com.example.digikala.viewmodel.HomeViewModel

@Composable
fun AmazingOffer(
    viewModel: HomeViewModel = hiltViewModel()
) {
    var amazingProductList by remember {
        mutableStateOf<List<AmazingProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val amazingProductResult by viewModel.amazingProducts.collectAsState()
    when (amazingProductResult) {
        is NetworkResult.Success -> {
            amazingProductList = amazingProductResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("3636", "AmazingOffer error : ${amazingProductResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true;
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.lightRed)
    ) {
        LazyRow(
            modifier = Modifier.background(MaterialTheme.colorScheme.lightRed)
        ) {
            item {
                AmazingOfferCard(
                    topImageResId = R.drawable.amazings,
                    bottomImageResId = R.drawable.box
                )
            }


            item {
                AmazingShowMoreCard()
            }
        }

    }
}