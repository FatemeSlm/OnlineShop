package com.example.digikala.ui.screens.item_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.screens.home.MostFavoriteCard
import com.example.digikala.ui.screens.home.MostFavoriteShowMore
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.ItemDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ItemDetailSimilarItems(
    categoryId: String,
    viewModel: ItemDetailViewModel = hiltViewModel()
) {

    var loading by remember {
        mutableStateOf(false)
    }
    var similarList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }

    LaunchedEffect(true) {
        viewModel.getSimilarItems(categoryId)

        viewModel.similarItems.collectLatest { similarItemsResult ->
            when (similarItemsResult) {
                is NetworkResult.Success -> {
                    similarList = similarItemsResult.data ?: emptyList()
                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("3636", "ItemDetailSimilarItems error : ${similarItemsResult.message}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }

        }
    }


    if (loading) {
        Loading(height = 200.dp, isDark = true)
    } else {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.small)
                .alpha(.4f)
                .shadow(2.dp),
            color = Color.LightGray
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.extraSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.similar_product),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.darkText,
                )
            }

            LazyRow {
                items(similarList) { item ->
                    MostFavoriteCard(item)
                }
                item {
                    MostFavoriteShowMore()
                }
            }
        }
    }
}