package com.example.ecommercemvvm

import com.example.ecommercemvvm.data.entity.ProductEntity
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProductList() : List<ProductEntity>
}