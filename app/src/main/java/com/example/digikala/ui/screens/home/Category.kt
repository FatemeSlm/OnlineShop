package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Category(viewModel: HomeViewModel = hiltViewModel()) {

    var loading by remember {
        mutableStateOf(false)
    }
    var categories by remember {
        mutableStateOf<List<Category>>(emptyList())
    }

    val categoryResult by viewModel.categories.collectAsState()
    when (categoryResult) {
        is NetworkResult.Success -> {
            categories = categoryResult.data ?: emptyList()
            loading = false;
        }

        is NetworkResult.Error -> {
            Log.e("3636", "Category error : ${categoryResult.message}")
            loading = false;
        }

        is NetworkResult.Loading -> {
            loading = true;
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = stringResource(id = R.string.category_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText
        )
        FlowRow(
            horizontalArrangement = Arrangement.SpaceAround,
            maxItemsInEachRow = 3,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (item in categories) {
                CategoryItem(item)
            }
        }
    }
}

@Composable
fun CategoryItem(item: Category) {
    Column(
        modifier = Modifier
            .size(100.dp, 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.image),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .padding(MaterialTheme.spacing.extraSmall)
        )
        Text(
            text = item.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.extraSmall),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
