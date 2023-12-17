package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.data.model.item_detail.NewComment
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
    val similarItems = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val newCommentResult = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getItemById(itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDetail.emit(repository.getItemById(itemId))
        }
    }

    fun getSimilarItems(categoryId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            similarItems.emit(repository.getSimilarItems(categoryId))
        }
    }

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch(Dispatchers.IO) {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }

}