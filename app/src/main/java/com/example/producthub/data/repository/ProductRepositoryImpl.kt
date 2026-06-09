package com.example.producthub.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.producthub.data.local.ProductDatabase
import com.example.producthub.data.mapper.toDomain
import com.example.producthub.data.mapper.toEntity
import com.example.producthub.data.paging.ProductRemoteMediator
import com.example.producthub.data.remote.ApiService
import com.example.producthub.domain.model.Product
import com.example.producthub.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.paging.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val db: ProductDatabase
) : ProductRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getProducts(): Flow<PagingData<Product>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = ProductRemoteMediator(api, db),
            pagingSourceFactory = {
                db.dao().pagingSource()
            }
        ).flow.map { pagingData ->

            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun getProductDetail(id: Int): Product {
        val response = api.getProductDetail(id)
        return response.data.toEntity().toDomain()
    }
}