package com.example.ecommercemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommercemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private val adapter = ProductCardListAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewProductList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.viewProductList.adapter = adapter
        updateUI(ProductListViewState.Content((1..3).map {
            ProductCardViewState("Playstation $it", "This is a nice console! Check it out", "200 US$")
        }))
//        updateUI(ProductListViewState.Loading)
//        updateUI(ProductListViewState.Error)

    }

    private fun updateUI(viewState: ProductListViewState){
        when(viewState){
            is ProductListViewState.Content->{
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                adapter.setData(viewState.productList)
            }
            ProductListViewState.Error->{
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
            }
            ProductListViewState.Loading->{
                binding.viewProductList.isVisible = false
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }


}