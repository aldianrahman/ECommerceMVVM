package com.example.ecommercemvvm

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes

data class ProductCardViewState(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl : String
)