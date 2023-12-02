package com.example.digikala.repository

import com.example.digikala.data.remote.AddressApiInterface
import com.example.digikala.data.remote.BaseApiResponse
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiInterface) :
    BaseApiResponse() {

//    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
//        safeApiCall {
//            api.login(loginRequest)
//        }

}

