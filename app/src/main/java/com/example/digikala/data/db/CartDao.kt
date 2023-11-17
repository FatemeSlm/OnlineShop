package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.data.model.cart.CartStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart: CartItem)

    @Query("select * from shopping_cart where cartStatus=:status")
    fun getAllItems(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun removeItem(item: CartItem)

    @Query("update shopping_cart set count=:newCount where id=:id")
    suspend fun updateItemCount(id:String, newCount:Int)

    @Query("update shopping_cart set cartStatus=:newStatus where id=:id")
    suspend fun updateItemStatus(id:String, newStatus:CartStatus)
}