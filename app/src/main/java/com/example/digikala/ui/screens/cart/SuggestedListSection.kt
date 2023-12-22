package com.example.digikala.ui.screens.cart

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.CartViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestedListSection(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {

    viewModel.getSuggestedList()

    var suggestedList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val suggestedItemsResult by viewModel.suggestedItems.collectAsState()
    when (suggestedItemsResult) {
        is NetworkResult.Success -> {
            suggestedList = suggestedItemsResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("3636", "SuggestedListSection error : ${suggestedItemsResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        Loading(height = 200.dp, isDark = true)
    } else {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.small)
                .background(MaterialTheme.colorScheme.searchBarBg)
        )
        Text(
            text = stringResource(id = R.string.suggestion_for_you),
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
        )

        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {

            for (item in suggestedList) {
                SuggestionItemCart(navController, item) {
                    viewModel.insertCartItem(
                        CartItem(
                            it.id,
                            it.name,
                            it.seller,
                            it.price,
                            it.discountPercent,
                            it.image,
                            1,
                            CartStatus.CURRENT_CART
                        )
                    )
                }
            }
        }
    }

}
