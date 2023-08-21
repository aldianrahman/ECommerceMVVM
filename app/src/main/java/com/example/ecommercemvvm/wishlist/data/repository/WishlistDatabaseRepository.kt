package com.example.ecommercemvvm.wishlist.data.repository

class WishlistDatabaseRepository: WishlisRepository {
    override fun isFavorite(productId: String): Boolean {
        return true
    }

    override fun addToWishlist(productId: String) {
        TODO("Not yet implemented")
    }

    override fun removeFromWishlist(productId: String) {
        TODO("Not yet implemented")
    }
}