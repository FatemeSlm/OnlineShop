package com.example.digikala.data.model.home

import com.google.gson.annotations.SerializedName

data class Slider(
    @SerializedName("_id")
    val id: String,
    val image: String,
    val url: String,
    val category: String,
    val priority: Int,
)
