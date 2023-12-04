package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CheckoutApiInterface {

    @GET("v1/getShippingCost")
    suspend fun getShippingCost(@Query("address") address: String): Response<ResponseResult<Int>>

}