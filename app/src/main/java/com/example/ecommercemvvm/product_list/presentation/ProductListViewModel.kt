package com.example.ecommercemvvm.product_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercemvvm.shared.data.repository.ProductRepository
import com.example.ecommercemvvm.wishlist.business.AddOrRemoveFromWishListUseCase
import com.example.ecommercemvvm.wishlist.business.IsProductInWishListUseCase
import com.example.ecommercemvvm.wishlist.data.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val isProductInWishListUseCase: IsProductInWishListUseCase,
    private val addOrRemoveFromWishListUseCase: AddOrRemoveFromWishListUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ProductListViewState>()
    val viewState: LiveData<ProductListViewState>
        get() = _viewState


    fun loadProductList() {
        viewModelScope.launch {
            _viewState.postValue(ProductListViewState.Loading)
            // Data call to fetch products
            val productList = repository.getProductList()
            _viewState.postValue(
                ProductListViewState.Content(
                    productList.map {
                        ProductCardViewState(
                            it.productId,
                            it.title,
                            it.description,
                            "US $ ${it.price}",
                            it.imageUrl,
                            isProductInWishListUseCase.execute(it.productId)
                        )
                    }
                ))
        }
    }

    fun favoriteIconClicked(productId: String) {
        viewModelScope.launch {
            addOrRemoveFromWishListUseCase.execute(productId)
            val currentViewState = _viewState.value
            (currentViewState as? ProductListViewState.Content)?.let { content ->
                _viewState.postValue(
                    content.updateFavoriteProduct(
                        productId,
                        isProductInWishListUseCase.execute(productId)
                    )
                )
            }
        }
    }
}