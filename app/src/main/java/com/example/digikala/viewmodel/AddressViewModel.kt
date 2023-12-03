package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.UserAddress
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.AddressRepository
import com.example.digikala.util.Constants.User_Token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())

    init {
        getUserAddress(User_Token)
    }

    private fun getUserAddress(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repository.getUserAddress(token))
        }
    }

}