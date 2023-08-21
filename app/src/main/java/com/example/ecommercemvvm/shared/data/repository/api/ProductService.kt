package com.example.ecommercemvvm.shared.data.repository.api

import com.example.ecommercemvvm.product_details.data.ProductDetailsEntity
import com.example.ecommercemvvm.product_list.data.ProductEntity
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductService {
//
//    @Headers(
//        "Content-Type: application/json",
//        "Access-Control-Request-Headers:*",
//        "api-key: 6438f8553f4da8ec3781253b",
//        "Accept: application/json"
//    )
//    @POST("findOne")
//    fun getProductList(@Body requestBody: JsonObject): Call<ProductEntity>


    @GET("products")
    suspend fun getProductList() : List<ProductEntity>

    @GET("productDetails")
    suspend fun getProductDetails(@Query("productId") productId: String): ProductDetailsEntity
}