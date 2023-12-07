package com.example.digikala.data.model.checkout

import com.example.digikala.data.model.cart.CartItem

data class OrderRequest(
    val token: String,
    val orderTotalPrice: Long,
    val orderTotalDiscount: Long,
    val orderAddress: String,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderProducts: List<CartItem>
)
