package com.example.digikala.ui.screens.cart

sealed class CartState<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T> : CartState<T>()
    class Success<T>(data: T?) : CartState<T>(data)
    class Error<T>(error: String) : CartState<T>(error = error)
}