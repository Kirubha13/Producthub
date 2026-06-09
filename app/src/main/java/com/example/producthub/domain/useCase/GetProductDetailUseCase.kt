package com.example.producthub.domain.useCase

import com.example.producthub.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase
@Inject constructor(
    private val repository:
    ProductRepository
){

    suspend operator fun invoke(
        id: Int
    ) =
        repository.getProductDetail(id)
}