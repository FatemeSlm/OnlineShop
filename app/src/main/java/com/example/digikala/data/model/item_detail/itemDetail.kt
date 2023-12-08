package com.example.digikala.data.model.item_detail

import com.google.gson.annotations.SerializedName

data class ItemDetail(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val seller: String,
    val category: String,
    val categoryId: String,
    val price: Long,
    val description: String,
    val discountPercent: Int,
    val star: Double,
    val starCount: Int,
    val viewCount: Int,
    val commentCount: Int,
    val questionCount: Int,
    val agreeCount: Int,
    val agreePercent: Int,
    val imageSlider: List<ImageSlider>,
    val colors: List<Color>,
    val comments: List<Comment>,
    val priceList: List<Price>,
    val technicalFeatures: Map<String, String>
)

data class ImageSlider(
    @SerializedName("_id")
    val id: String,
    val image: String,
    val productId: String
)

data class Color(
    @SerializedName("_id")
    val id: String,
    val color: String,
    val code: String
)

data class Comment(
    @SerializedName("_id")
    val id: String,
    val title: String,
    val description: String,
    val star: Int,
    val productId: String,
    val userId: String,
    val userName: String,
    val updatedAt: String,
    val createdAt: String,
    @SerializedName("__v")
    val v: Int
)

data class Price(
    val persianDate: String,
    val price: Int
)