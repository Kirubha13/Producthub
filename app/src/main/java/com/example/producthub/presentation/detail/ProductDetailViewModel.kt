package com.example.producthub.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.producthub.domain.useCase.GetProductDetailUseCase
import com.example.producthub.domain.useCase.SessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val sessionUseCases: SessionUseCases
) : ViewModel() {

    private val productId =
        checkNotNull(
            savedStateHandle.get<String>("id")
        ).toInt()
    val userEmail =
        sessionUseCases
            .getUserEmailUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ""
            )


    var state by mutableStateOf(
        DetailUiState(isLoading = true)
    )
        private set

    init {
        observeUserEmail()
        loadProduct()
    }

    private fun observeUserEmail() {
        viewModelScope.launch {
            sessionUseCases
                .getUserEmailUseCase()
                .collect { email ->
                    state = state.copy(
                        userEmail = email
                    )
                }
        }
    }

    private fun loadProduct() {

        viewModelScope.launch {

            try {

                val product =
                    getProductDetailUseCase(productId)

                state = DetailUiState(
                    product = product,
                    isLoading = false
                )

            } catch (e: Exception) {

                state = DetailUiState(
                    error = e.message ?: "Unknown error",
                    isLoading = false
                )
            }
        }
    }
}