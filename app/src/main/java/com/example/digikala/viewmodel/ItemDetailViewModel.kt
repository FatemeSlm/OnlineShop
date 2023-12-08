package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.ItemDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(private val repository: ItemDetailRepository) :
    ViewModel() {

    val itemDetail = MutableStateFlow<NetworkResult<ItemDetail>>(NetworkResult.Loading())

    fun getItemById(itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDetail.emit(repository.getItemById(itemId))
        }
    }

}