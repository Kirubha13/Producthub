package com.example.producthub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.producthub.domain.useCase.GetProductsUseCase
import com.example.producthub.domain.useCase.SessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getProductsUseCase: GetProductsUseCase,
    private val sessionUseCases:
    SessionUseCases
) : ViewModel() {

    val products =
        getProductsUseCase()
            .cachedIn(viewModelScope)

    val userEmail =
        sessionUseCases
            .getUserEmailUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ""
            )


    fun logout(
        onLoggedOut: () -> Unit
    ) {

        viewModelScope.launch {

            sessionUseCases.logout()

            onLoggedOut()
        }
    }
}