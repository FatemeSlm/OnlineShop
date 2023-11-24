package com.example.digikala.data.model.profile

data class LoginResponse(
    val phone: String,
    val id: String,
    val role: String,
    val token: String
)
