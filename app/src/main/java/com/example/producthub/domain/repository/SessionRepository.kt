package com.example.producthub.domain.repository

import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun login(email: String)

    suspend fun logout()

    fun getEmail(): Flow<String>

    fun isLoggedIn(): Flow<Boolean>

    suspend fun isUserLoggedIn(): Boolean?
}