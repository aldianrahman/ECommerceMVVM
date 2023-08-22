package com.example.ecommercemvvm.wishlist.data.repository

interface WishlistRepository {

    suspend fun isFavorite(productId:String): Boolean
    suspend fun addToWishlist(productId: String)
    suspend fun removeFromWishlist(productId: String)

}