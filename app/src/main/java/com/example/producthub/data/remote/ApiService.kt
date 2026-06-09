package com.example.producthub.data.remote

import com.example.producthub.data.remote.dto.ProductDetailResponseDto
import com.example.producthub.data.remote.dto.ProductListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/public/randomproducts")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ProductListResponseDto

    @GET("api/v1/public/randomproducts/{id}")
    suspend fun getProductDetail(
        @Path("id") id: Int
    ): ProductDetailResponseDto
}