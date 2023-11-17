package com.example.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digikala.data.model.cart.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
}