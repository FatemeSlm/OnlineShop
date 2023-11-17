package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val suggestedItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    val currentCartItems : Flow<List<CartItem>> = repository.currentCartItems
    val nextListItems :Flow<List<CartItem>> = repository.nextListItems

    fun getSuggestedList() {
        viewModelScope.launch {
            suggestedItems.emit(repository.getSuggestedItems())
        }
    }

    fun insertCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(cartItem)
        }
    }

    fun removeItem(cartItem: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeItem(cartItem)
        }
    }

    fun updateItemCount(id:String, newCount:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItemCount(id, newCount)
        }
    }

    fun updateItemStatus(id:String, newStatus:CartStatus){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItemStatus(id, newStatus)
        }
    }


}