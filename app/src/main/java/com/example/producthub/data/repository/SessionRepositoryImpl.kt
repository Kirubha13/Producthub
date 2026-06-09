package com.example.producthub.data.repository

import com.example.producthub.core.datastore.SessionManager
import com.example.producthub.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow

class SessionRepositoryImpl(
    private val session: SessionManager
) : SessionRepository {

    override suspend fun login(email: String) {
        session.login(email)
    }

    override suspend fun logout() {
        session.logout()
    }

    override fun getEmail(): Flow<String> {
        return session.emailFlow
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return session.loginFlow
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return session.isUserLoggedIn()
    }
}