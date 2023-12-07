package com.example.digikala.repository

import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.data.model.checkout.OrderRequest
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.CheckoutApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) :
    BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
            api.getShippingCost(address)
        }

    suspend fun addNewOrder(orderRequest: OrderRequest): NetworkResult<String> =
        safeApiCall {
            api.addNewOrder(orderRequest)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchaseRequest): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }


}

