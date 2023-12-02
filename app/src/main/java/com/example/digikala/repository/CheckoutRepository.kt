package com.example.digikala.repository

import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.CheckoutApiInterface
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) :
    BaseApiResponse() {

//    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
//        safeApiCall {
//            api.login(loginRequest)
//        }

}

