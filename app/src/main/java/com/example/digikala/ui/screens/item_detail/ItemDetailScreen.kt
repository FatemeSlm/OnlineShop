package com.example.digikala.ui.screens.item_detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.viewmodel.ItemDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ItemDetailScreen(
    navController: NavHostController,
    itemId: String,
    viewModel: ItemDetailViewModel = hiltViewModel()
) {

    var loading by remember { mutableStateOf(false) }

    var itemDetail by remember {
        mutableStateOf<ItemDetail?>(null)
    }

    LaunchedEffect(Dispatchers.Main) {

        viewModel.getItemById(itemId)

        viewModel.itemDetail.collectLatest { itemDetailResult ->
            when (itemDetailResult) {
                is NetworkResult.Success -> {
                    itemDetail = itemDetailResult.data
                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("3636", "ItemDetailScreen error : ${itemDetailResult.message}}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }
}