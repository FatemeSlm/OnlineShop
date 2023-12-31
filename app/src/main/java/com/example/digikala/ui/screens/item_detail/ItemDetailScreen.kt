package com.example.digikala.ui.screens.item_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.viewmodel.ItemDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
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

    //ui
    if (loading) {
        val config = LocalConfiguration.current
        Loading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        if (itemDetail != null) {
            Scaffold(
                bottomBar = {
                    ItemDetailBottomBar(itemDetail = itemDetail!!, navController = navController)
                }
            ) {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 70.dp)
                ) {

                    item { ItemDetailTopSlider(imageList = itemDetail!!.imageSlider) }
                    item { ItemDetailHeader(itemDetail!!) }
                    item { ItemDetailSelectColor(itemDetail!!.colors) }
                    item { ItemDetailSellerInfo() }
                    item {
                        ItemDetailSimilarItems(
                            categoryId = itemDetail!!.categoryId,
                            navController = navController
                        )
                    }
                    item {
                        ItemDetailDesc(
                            navController,
                            itemDetail!!.description,
                            itemDetail!!.technicalFeatures
                        )
                    }
                    item {
                        ItemDetailComment(
                            navController,
                            itemDetail!!.comments,
                            itemDetail!!.commentCount,
                            itemDetail!!.id
                        )
                    }
                    item { ItemDetailSetComment(navController, itemDetail!!) }
                }
            }
        }
    }
}