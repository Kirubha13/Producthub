package com.example.producthub.di

import com.example.producthub.domain.repository.ProductRepository
import com.example.producthub.domain.repository.SessionRepository
import com.example.producthub.domain.useCase.GetProductDetailUseCase
import com.example.producthub.domain.useCase.GetProductsUseCase
import com.example.producthub.domain.useCase.GetUserEmailUseCase
import com.example.producthub.domain.useCase.LogOutUseCase
import com.example.producthub.domain.useCase.LoginUseCase
import com.example.producthub.domain.useCase.SessionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideProductUseCases(
        repo: ProductRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(repo)
    }

    @Provides
    fun provideProductDetailUseCase(
        repo: ProductRepository
    ): GetProductDetailUseCase {
        return GetProductDetailUseCase(repo)
    }

    @Provides
    fun provideSessionUseCases(
        repo: SessionRepository
    ): SessionUseCases {
        return SessionUseCases(
            login = LoginUseCase(repo),
            logout = LogOutUseCase(repo),
            getUserEmailUseCase = GetUserEmailUseCase(repo)
        )
    }
}