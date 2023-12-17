package com.example.digikala.repository

import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.item_detail.Comment
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.data.model.item_detail.NewComment
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.ItemDetailApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class ItemDetailRepository @Inject constructor(private val api: ItemDetailApiInterface) :
    BaseApiResponse() {

    suspend fun getItemById(itemId: String): NetworkResult<ItemDetail> =
        safeApiCall {
            api.getItemById(itemId)
        }

    suspend fun getSimilarItems(categoryId: String): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSimilarItems(categoryId)
        }

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }

    suspend fun getAllItemComments(
        itemId: String,
        pageSize: Int,
        pageNumber: Int
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getAllItemComments(itemId, pageSize, pageNumber)
        }


}

