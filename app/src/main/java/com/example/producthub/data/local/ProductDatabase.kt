package com.example.producthub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.producthub.data.local.dao.ProductDao
import com.example.producthub.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun dao(): ProductDao
}