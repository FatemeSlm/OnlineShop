package com.example.digikala.repository

import com.example.digikala.data.model.home.AmazingProduct
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

    suspend fun getAmazingProducts(): NetworkResult<List<AmazingProduct>> =
        safeApiCall {
            api.getAmazingProducts()
        }

    suspend fun getSuperMarketAmazingProducts(): NetworkResult<List<AmazingProduct>> =
        safeApiCall {
            api.getSuperMarketAmazingProducts()
        }

    suspend fun getProposalBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getProposalBanners()
        }

    suspend fun getCategories(): NetworkResult<List<Category>> =
        safeApiCall {
            api.getCategories()
        }

    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }

    suspend fun getBestSellerProducts(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getBestSellerProducts()
        }

    suspend fun getMostVisitedProducts(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }
}

