package com.example.digikala.ui.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.CenterBannerCard
import com.example.digikala.ui.component.Loading
import com.example.digikala.viewmodel.HomeViewModel

@Composable
fun CenterBanner(
    bannerNumber: Int,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var loading by remember {
        mutableStateOf(false)
    }
    var centerBannerList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    val centerBannerResult by viewModel.centerBanners.collectAsState()
    when (centerBannerResult) {
        is NetworkResult.Success -> {
            centerBannerList = centerBannerResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("3636", "CenterBanner error : ${centerBannerResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        Loading(height = 170.dp, isDark = true)
    } else {
        if (centerBannerList.isNotEmpty()) {
            CenterBannerCard(imageUrl = centerBannerList[bannerNumber - 1].image)
        }
    }
}