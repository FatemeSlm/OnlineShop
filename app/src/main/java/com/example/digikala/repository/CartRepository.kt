package com.example.digikala.repository

import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.CartApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CartRepository @Inject constructor(private val api: CartApiInterface) : BaseApiResponse() {

}