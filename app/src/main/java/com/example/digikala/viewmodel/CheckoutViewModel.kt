package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.checkout.ConfirmPurchaseRequest
import com.example.digikala.data.model.checkout.OrderRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepository) :
    ViewModel() {

    val shippingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())
    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())
    val purchaseResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getShippingCost(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            shippingCost.emit(repository.getShippingCost(address))
        }
    }

    fun addNewOrder(orderRequest: OrderRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            orderResponse.emit(repository.addNewOrder(orderRequest))
        }
    }

    fun confirmPurchase(confirmPurchase: ConfirmPurchaseRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            purchaseResponse.emit(
                repository.confirmPurchase(
                    confirmPurchase
                )
            )
        }
    }

}