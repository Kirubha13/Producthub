package com.example.producthub.presentation.detail

import com.example.producthub.domain.model.Product

data class DetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val userEmail: String = "",
    val error: String? = null
)
