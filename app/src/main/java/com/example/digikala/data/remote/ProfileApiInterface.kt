package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>

}