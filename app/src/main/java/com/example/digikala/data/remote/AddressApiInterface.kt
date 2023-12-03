package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.UserAddress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(@Query("token") token: String)
            : Response<ResponseResult<List<UserAddress>>>
}