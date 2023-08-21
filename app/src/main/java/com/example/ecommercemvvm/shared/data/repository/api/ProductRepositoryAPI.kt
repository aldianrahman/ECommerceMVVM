package com.example.ecommercemvvm.shared.data.repository.api

import android.util.Log
import com.example.ecommercemvvm.product_details.business.ProductDetails
import com.example.ecommercemvvm.product_list.business.Product
import com.example.ecommercemvvm.product_list.data.ProductEntity
import com.example.ecommercemvvm.shared.data.repository.ProductRepository
import com.example.ecommercemvvm.product_list.presentation.ProductCardViewState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import kotlinx.coroutines.withContext
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryAPI @Inject constructor(private val service: ProductService) :
    ProductRepository {

    override suspend fun getProductList(): List<Product> {
        return withContext(Dispatchers.IO) {
            service.getProductList().map {
                Product(
                    it.title,
                    it.description,
                    it.price,
                    it.imageUrl,
                    it.id
                )
            }
        }
    }

    override suspend fun getProductDetails(productId: String): ProductDetails {
        return withContext(Dispatchers.IO) {
            service.getProductDetails(productId).run {
                ProductDetails(
                    this.title,
                    this.description,
                    this.full_description,
                    "US $ ${this.price}",
                    this.imageUrl,
                    this.pros,
                    this.cons
                )
            }
        }
    }
}