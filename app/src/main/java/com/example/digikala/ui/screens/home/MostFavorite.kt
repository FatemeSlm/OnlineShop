package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.HomeViewModel

@Composable
fun MostFavorite(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var loading by remember {
        mutableStateOf(false)
    }
    var mostFavoriteList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }

    val mostFavoriteResult by viewModel.mostFavorite.collectAsState()
    when (mostFavoriteResult) {
        is NetworkResult.Success -> {
            mostFavoriteList = mostFavoriteResult.data ?: emptyList()
            loading = false;
        }

        is NetworkResult.Error -> {
            Log.e("3636", "MostFavorite error : ${mostFavoriteResult.message}")
            loading = false;
        }

        is NetworkResult.Loading -> {
            loading = true;
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.favorite_product),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
            )
            Text(
                text = stringResource(id = R.string.see_all),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkCyan,
            )
        }

        LazyRow{
            items(mostFavoriteList){item ->
                MostFavoriteCard(item)
            }
            item {
                MostFavoriteShowMore()
            }
        }
    }
}