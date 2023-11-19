package com.example.digikala.data.model.cart

data class CartDetail(
    val totalCount:Int,
    val totalPrice:Long,
    val totalDiscount:Long,
    val payablePrice:Long
)