package com.example.ecommercemvvm.product_list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercemvvm.R
import com.example.ecommercemvvm.databinding.ProductCardBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class ProductCardListAdapter(
    val onItemClicked: (ProductCardViewState) -> Unit,
    val onFavoriteIconClicked: (ProductCardViewState) -> Unit
) : RecyclerView.Adapter<ProductCardListAdapter.ViewHolder>() {


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
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productCardViewState: ProductCardViewState) {
            val bind = ProductCardBinding.bind(itemView)
            itemView.setOnClickListener {
                onItemClicked(productCardViewState)
            }
            bind.apply {
                viewProductName.text = productCardViewState.title
                viewProductDescription.text = productCardViewState.description
                productPrice.text = productCardViewState.price
                viewWishlistIcon.setOnClickListener{
                    onFavoriteIconClicked.invoke(
                        productCardViewState
                    )
                }
                viewWishlistIcon.setImageDrawable(
                    if (productCardViewState.isFavorite) {
                        ResourcesCompat.getDrawable(
                            viewWishlistIcon.resources,
                            R.drawable.ic_baseline_favorite,
                            null
                        )
                    } else {
                        ResourcesCompat.getDrawable(
                            viewWishlistIcon.resources,
                            R.drawable.ic_baseline_favorite_disabled,
                            null
                        )
                    }
                )
                Picasso.get()
                    .load(productCardViewState.imageUrl)
                    .into(productImage, object : Callback {
                        override fun onSuccess() {
                            // Hide the progress bar when the image is loaded
                            loadingViewImage.visibility = View.GONE
                            productImage.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception?) {
                            // Handle error if image loading fails
                        }
                    })
//                Glide.with(productImage)
//                    .asBitmap()
//                    .load(productCardViewState.imageUrl)
//                    .into(BitmapImageViewTarget(productImage))
            }
        }

    }
}