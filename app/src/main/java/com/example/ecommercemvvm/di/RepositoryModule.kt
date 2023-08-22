package com.example.ecommercemvvm.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.example.ecommercemvvm.shared.data.repository.api.ApiClient
import com.example.ecommercemvvm.shared.data.repository.ProductRepository
import com.example.ecommercemvvm.shared.data.repository.api.ProductRepositoryAPI
import com.example.ecommercemvvm.shared.data.repository.api.ProductService
import com.example.ecommercemvvm.wishlist.data.repository.WishlistDatabaseRepository
import com.example.ecommercemvvm.wishlist.data.repository.WishlistRepository
import com.example.ecommercemvvm.wishlist.data.repository.database.AppDatabase
import com.example.ecommercemvvm.wishlist.data.repository.database.WishListDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun providesWishlistRepository(
        databaseRepository: WishlistDatabaseRepository
    ): WishlistRepository = databaseRepository

    @Provides
    fun providesWishlistDatabaseRepository(databaseDao: WishListDAO): WishlistDatabaseRepository{
        return WishlistDatabaseRepository(databaseDao)
    }

    @Provides
    fun providesWishlistDao(
        @ApplicationContext context: Context
    ):WishListDAO {
        val db = Room.databaseBuilder(context, AppDatabase::class.java,
        "ecommerce-database").build()
        return db.wishListDao()
    }

}