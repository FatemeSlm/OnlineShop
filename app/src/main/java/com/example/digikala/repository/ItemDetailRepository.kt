package com.example.digikala.repository

import com.example.digikala.data.model.item_detail.ItemDetail
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

}

