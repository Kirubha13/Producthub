package com.example.producthub.data.remote.dto

data class ProductListResponseDto(
    val statusCode: Int,
    val data: ProductListDataDto
)

data class ProductListDataDto(
    val page: Int,
    val limit: Int,
    val totalPages: Int,
    val previousPage: Boolean,
    val nextPage: Boolean,
    val totalItems: Int,
    val currentPageItems: Int,
    val data: List<ProductDto>
)
