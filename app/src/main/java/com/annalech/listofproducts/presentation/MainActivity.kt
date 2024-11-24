package com.annalech.listofproducts.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapterShopList: AdapterShopList

    private var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)  //доступ к классу с лив дата

        viewModel.shopListLiveData.observe(this){
            Log.d("ViewModelTest", it.toString())
            adapterShopList.shopList = it


        }



    }

    private fun setupRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_shopList)
        adapterShopList = AdapterShopList()
        recyclerView.adapter = adapterShopList

    }


}

