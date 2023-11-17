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

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun insertCartItem(cartItem: CartItem){
        dao.insertCartItem(cartItem)
    }

}