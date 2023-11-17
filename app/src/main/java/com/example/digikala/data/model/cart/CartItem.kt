package com.example.digikala.data.model.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.util.Constants.Shopping_Cart_Table

@Entity(tableName = Shopping_Cart_Table)
data class CartItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
    val count: Int,
    val cartStatus: CartStatus
)
