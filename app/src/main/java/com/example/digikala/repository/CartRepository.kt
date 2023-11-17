package com.example.digikala.repository

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.CartApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val api: CartApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextListItems = dao.getAllItems(CartStatus.NEXT_CART)

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun insertCartItem(cartItem: CartItem){
        dao.insertCartItem(cartItem)
    }

    suspend fun removeItem(cartItem: CartItem){
        dao.removeItem(cartItem)
    }

    suspend fun updateItemCount(id:String, newCount:Int){
        dao.updateItemCount(id, newCount)
    }

    suspend fun updateItemStatus(id:String, newStatus:CartStatus){
        dao.updateItemStatus(id, newStatus)
    }

}