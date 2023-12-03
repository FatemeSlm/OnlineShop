package com.example.digikala.repository

import com.example.digikala.data.model.UserAddress
import com.example.digikala.data.remote.AddressApiInterface
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiInterface) :
    BaseApiResponse() {

    suspend fun getUserAddress(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
            api.getUserAddressList(token)
        }

}

