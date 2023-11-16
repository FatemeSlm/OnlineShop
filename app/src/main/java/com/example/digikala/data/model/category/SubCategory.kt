package com.example.digikala.data.model.category

import com.google.gson.annotations.SerializedName

data class SubCategory(
    val tool: List<Sub>,
    val digital: List<Sub>,
    val mobile: List<Sub>,
    val supermarket: List<Sub>,
    val fashion: List<Sub>,
    val native: List<Sub>,
    val toy: List<Sub>,
    val beauty: List<Sub>,
    val home: List<Sub>,
    val book: List<Sub>,
    val sport: List<Sub>,
)

data class Sub(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val count: Int,
    val image: String
)