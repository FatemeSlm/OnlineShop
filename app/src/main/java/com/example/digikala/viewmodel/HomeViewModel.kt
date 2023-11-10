package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.AmazingProduct
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingProducts =
        MutableStateFlow<NetworkResult<List<AmazingProduct>>>(NetworkResult.Loading())
    val superMarketAmazingProducts =
        MutableStateFlow<NetworkResult<List<AmazingProduct>>>(NetworkResult.Loading())

    val proposalBanner = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val categories = MutableStateFlow<NetworkResult<List<Category>>>(NetworkResult.Loading())

    val centerBanners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestSellers = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostVisited = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            // fire and forget
            launch {
                slider.emit(repository.getSlider())
            }

            launch {
                amazingProducts.emit(repository.getAmazingProducts())
            }

            launch {
                superMarketAmazingProducts.emit(repository.getSuperMarketAmazingProducts())
            }

            launch {
                proposalBanner.emit(repository.getProposalBanners())
            }

            launch {
                categories.emit(repository.getCategories())
            }

            launch {
                centerBanners.emit(repository.getCenterBanners())
            }

            launch {
                bestSellers.emit(repository.getBestSellerProducts())
            }
            launch {
                mostVisited.emit(repository.getMostVisitedProducts())
            }
        }

    }
}