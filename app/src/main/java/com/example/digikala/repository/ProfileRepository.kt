package com.example.digikala.repository

import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ProfileApiInterface) :
    BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

}

