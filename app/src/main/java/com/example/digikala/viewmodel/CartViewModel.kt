package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.cart.CartDetail
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CartRepository
import com.example.digikala.ui.screens.cart.CartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val suggestedItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val cartDetail = MutableStateFlow(CartDetail(0, 0, 0, 0))

    private val _currentCartItems = MutableStateFlow<CartState<List<CartItem>>>(CartState.Loading())
    val currentCartItems: StateFlow<CartState<List<CartItem>>> = _currentCartItems

    private val _nextCardItems = MutableStateFlow<CartState<List<CartItem>>>(CartState.Loading())
    val nextCartItems: StateFlow<CartState<List<CartItem>>> = _nextCardItems

    val currentCartItemsCount = repository.currentCartItemsCount
    val nextCartItemsCount = repository.nextCartItemsCount

    val cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                try {
                    repository.currentCartItems.collectLatest {
                        _currentCartItems.emit(CartState.Success(it))
                        cartItems.emit(it)
                    }
                } catch (exp: Exception) {
                    _currentCartItems.emit(CartState.Error(exp.toString()))
                }
            }

            launch {
                repository.currentCartItems.collectLatest {
                    calculateCartDetail(it)
                }
            }

            launch {
                try {
                    repository.nextCardItems.collectLatest {
                        _nextCardItems.emit(CartState.Success(it))
                    }
                } catch (exp: Exception) {
                    _nextCardItems.emit(CartState.Error(exp.toString()))
                }
            }
        }
    }

    private fun calculateCartDetail(items: List<CartItem>) {
        var totalCount = 0
        var totalPrice = 0L
        var totalDiscount = 0L
        items.forEach { item ->
            totalPrice += item.price * item.count
            totalDiscount += (item.price * item.discountPercent / 100) * item.count
            totalCount += item.count
        }
        val payablePrice: Long = totalPrice - totalDiscount
        cartDetail.value = CartDetail(totalCount, totalPrice, totalDiscount, payablePrice)
    }

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

    fun removeItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeItem(cartItem)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }

    fun updateItemCount(id: String, newCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItemCount(id, newCount)
        }
    }

    fun updateItemStatus(id: String, newStatus: CartStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItemStatus(id, newStatus)
        }
    }
}