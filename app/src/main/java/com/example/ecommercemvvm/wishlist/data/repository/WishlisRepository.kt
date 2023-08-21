package com.example.ecommercemvvm.wishlist.data.repository

interface WishlisRepository {

    fun isFavorite(productId:String): Boolean
    fun addToWishlist(productId: String)
    fun removeFromWishlist(productId: String)

}