package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.item_detail.ItemDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemDetailApiInterface {

    @GET("v1/getProductById")
    suspend fun getItemById(@Query("id") itemId: String): Response<ResponseResult<ItemDetail>>

    @GET("v1/getSimilarProducts")
    suspend fun getSimilarItems(@Query("categoryId") categoryId: String): Response<ResponseResult<List<StoreProduct>>>
}