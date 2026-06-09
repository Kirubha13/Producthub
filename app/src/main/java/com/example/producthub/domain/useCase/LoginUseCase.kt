package com.example.producthub.domain.useCase

import com.example.producthub.domain.repository.SessionRepository
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val repository:
    SessionRepository
){

    suspend operator fun invoke(
        email: String
    ){
        repository.login(email)
    }
}