package com.example.digikala.data.model.home

import com.google.gson.annotations.SerializedName

data class StoreProduct(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String
)
