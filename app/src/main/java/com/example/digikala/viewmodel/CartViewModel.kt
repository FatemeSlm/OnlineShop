package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val suggestedItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    fun getSuggestedList() {
        viewModelScope.launch {

            launch {
                suggestedItems.emit(repository.getSuggestedItems())
            }
        }
    }
}