package com.example.producthub.domain.repository

import androidx.paging.PagingData
import com.example.producthub.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<PagingData<Product>>

    suspend fun getProductDetail(id: Int): Product
}