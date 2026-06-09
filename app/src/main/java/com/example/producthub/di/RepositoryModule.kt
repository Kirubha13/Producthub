package com.example.producthub.di

import com.example.producthub.core.datastore.SessionManager
import com.example.producthub.data.local.ProductDatabase
import com.example.producthub.data.remote.ApiService
import com.example.producthub.data.repository.ProductRepositoryImpl
import com.example.producthub.data.repository.SessionRepositoryImpl
import com.example.producthub.domain.repository.ProductRepository
import com.example.producthub.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(
        api: ApiService,
        db: ProductDatabase
    ): ProductRepository {
        return ProductRepositoryImpl(api, db)
    }

    @Provides
    fun provideSessionRepository(
        session: SessionManager
    ): SessionRepository {
        return SessionRepositoryImpl(session)
    }
}