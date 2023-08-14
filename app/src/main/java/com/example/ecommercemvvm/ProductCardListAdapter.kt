package com.example.ecommercemvvm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercemvvm.databinding.ProductCardBinding
import com.squareup.picasso.Picasso
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class ProductCardListAdapter : RecyclerView.Adapter<ProductCardListAdapter.ViewHolder>() {


    private var data: List<ProductCardViewState> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(productList: List<ProductCardViewState>) {
        this.data = productList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productCardViewState: ProductCardViewState) {
            val bind =  ProductCardBinding.bind(itemView)
            bind.apply {
                viewProductName.text = productCardViewState.title
                viewProductDescription.text = productCardViewState.description
                productPrice.text = productCardViewState.price
                Picasso.get().load(productCardViewState.imageUrl).into(productImage)
            }

        }



    }
}