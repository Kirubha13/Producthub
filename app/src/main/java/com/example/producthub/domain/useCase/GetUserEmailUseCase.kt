package com.example.producthub.domain.useCase

import com.example.producthub.domain.repository.SessionRepository
import javax.inject.Inject

class GetUserEmailUseCase
@Inject constructor(
    private val repository:
    SessionRepository
){
    operator fun invoke() = repository.getEmail()
}
