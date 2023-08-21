package com.example.ecommercemvvm.shared.data.repository

import com.example.ecommercemvvm.product_details.business.ProductDetails
import com.example.ecommercemvvm.product_list.business.Product
import com.example.ecommercemvvm.product_list.presentation.ProductCardViewState

interface ProductRepository {
    suspend fun getProductList(): List<Product>

    suspend fun getProductDetails(productId: String): ProductDetails
}