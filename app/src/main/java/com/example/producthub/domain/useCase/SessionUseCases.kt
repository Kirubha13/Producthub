package com.example.producthub.domain.useCase

data class SessionUseCases(
    val login: LoginUseCase,
    val logout: LogOutUseCase,
    val getUserEmailUseCase: GetUserEmailUseCase
)