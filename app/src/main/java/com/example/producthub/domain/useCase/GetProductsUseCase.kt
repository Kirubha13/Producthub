package com.example.producthub.domain.useCase

import androidx.paging.PagingData
import com.example.producthub.domain.model.Product
import com.example.producthub.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase
@Inject constructor(
    private val repository:
    ProductRepository
){
    operator fun invoke(): Flow<PagingData<Product>> {
        return repository.getProducts()
    }
}