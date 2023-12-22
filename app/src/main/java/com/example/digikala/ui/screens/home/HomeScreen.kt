package com.example.digikala.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.util.LocaleUtils
import com.example.digikala.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController = navController)
}

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LocaleUtils.setLocale(LocalContext.current, App_Language)

    LaunchedEffect(true) {
        refreshDataFromServer(viewModel)
    }

    SwipeRefreshSection(viewModel, navController)
}

@Composable
private fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavHostController) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        refreshScope.launch {
            refreshDataFromServer(viewModel)
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            item { SearchBar() }
            item { TopSlider() }
            item { ShowcaseSection(navController) }
            item { AmazingOffer(navController) }
            item { ProposalBanner() }
            item { SuperMarketAmazingOffer(navController) }
            item { Category() }
            item { CenterBanner(bannerNumber = 1) }
            item { BestSellers(navController) }
            item { CenterBanner(bannerNumber = 2) }
            item { MostFavorite(navController) }
            item { CenterBanner(bannerNumber = 3) }
            item { MostVisited(navController) }
            item { CenterBanner(bannerNumber = 4) }
            item { CenterBanner(bannerNumber = 5) }
            item { MostDiscount(navController) }
        }
    }
}

private suspend fun refreshDataFromServer(viewModel: HomeViewModel) {
    viewModel.getAllDataFromServer()
}