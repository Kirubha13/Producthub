package com.example.producthub.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.producthub.data.local.ProductDatabase
import com.example.producthub.data.local.entity.ProductEntity
import com.example.producthub.data.mapper.toEntity
import com.example.producthub.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val api: ApiService,
    private val db: ProductDatabase
) : RemoteMediator<Int, ProductEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {

        return try {

            when (loadType) {

                LoadType.PREPEND -> {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }

                LoadType.APPEND -> {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }

                LoadType.REFRESH -> Unit
            }

            val response = api.getProducts(
                page = 1,
                limit = 100
            )

            val products = response.data.data

            db.withTransaction {

                db.dao().clearAll()

                db.dao().insertAll(
                    products.map { it.toEntity() }
                )
            }

            MediatorResult.Success(
                endOfPaginationReached = true
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}