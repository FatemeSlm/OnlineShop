package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.item_detail.Comment
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.data.model.item_detail.NewComment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ItemDetailApiInterface {

    @GET("v1/getProductById")
    suspend fun getItemById(@Query("id") itemId: String): Response<ResponseResult<ItemDetail>>

    @GET("v1/getSimilarProducts")
    suspend fun getSimilarItems(@Query("categoryId") categoryId: String): Response<ResponseResult<List<StoreProduct>>>

    @POST("v1/setNewComment")
    suspend fun setNewComment(@Body newComment: NewComment): Response<ResponseResult<String>>

    @GET("v1/getAllProductComments")
    suspend fun getAllItemComments(
        @Query("id") id: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
    ): Response<ResponseResult<List<Comment>>>
}