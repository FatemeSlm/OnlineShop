package com.example.digikala.data.model.checkout

data class ConfirmPurchaseRequest(
    val orderId: String,
    val token: String,
    val transactionId: String
)
