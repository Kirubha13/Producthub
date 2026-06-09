package com.example.producthub.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class SessionManager @Inject constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("session")

    companion object {
        val EMAIL = stringPreferencesKey("email")
        val LOGGED_IN = booleanPreferencesKey("logged_in")
    }

    suspend fun login(email: String) {
        context.dataStore.edit {
            it[EMAIL] = email
            it[LOGGED_IN] = true
        }
    }

    suspend fun logout() {
        context.dataStore.edit { it.clear() }
    }

    val emailFlow = context.dataStore.data.map { it[EMAIL] ?: "" }

    val loginFlow = context.dataStore.data
        .map { it[LOGGED_IN] ?: false }

    suspend fun isUserLoggedIn(): Boolean {
        return context.dataStore.data
            .first()[LOGGED_IN] ?: false
    }
}