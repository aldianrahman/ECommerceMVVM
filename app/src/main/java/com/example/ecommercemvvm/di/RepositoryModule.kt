package com.example.ecommercemvvm.di

import com.example.ecommercemvvm.shared.data.repository.api.ApiClient
import com.example.ecommercemvvm.shared.data.repository.ProductRepository
import com.example.ecommercemvvm.shared.data.repository.api.ProductRepositoryAPI
import com.example.ecommercemvvm.shared.data.repository.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesProductService(): ProductService = ApiClient.getService()

    @Provides
    fun providesProductRepositoryAPI(
        service: ProductService
    ): ProductRepositoryAPI = ProductRepositoryAPI(service)

    @Provides
    fun providesProductRepository(
        productRepositoryAPI: ProductRepositoryAPI
    ): ProductRepository = productRepositoryAPI
}