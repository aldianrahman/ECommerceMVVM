package com.example.ecommercemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommercemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private val adapter = ProductCardListAdapter()

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewProductList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.viewProductList.adapter = adapter
        viewModel.viewState.observe(this){viewState->
            updateUI(viewState)
        }
        viewModel.loadProductList()
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