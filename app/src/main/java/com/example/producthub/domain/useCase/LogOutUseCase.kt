package com.example.producthub.domain.useCase

import com.example.producthub.domain.repository.SessionRepository
import javax.inject.Inject

class LogOutUseCase
@Inject constructor(
    private val repository:
    SessionRepository
){
    suspend operator fun invoke(){
        repository.logout()
    }
}