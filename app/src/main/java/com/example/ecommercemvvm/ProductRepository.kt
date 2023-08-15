package com.example.ecommercemvvm

interface ProductRepository {
    suspend fun getProductList(): List<ProductCardViewState>
}