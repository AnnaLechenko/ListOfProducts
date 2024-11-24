package com.annalech.listofproducts.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.annalech.listofproducts.R

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
        val recyclerViewList = findViewById<RecyclerView>(R.id.recyclerView_shopList)
        adapterShopList = AdapterShopList()
        recyclerViewList.adapter = adapterShopList

        recyclerViewList.recycledViewPool.setMaxRecycledViews(adapterShopList.enabledCONST, adapterShopList.max_poolCONST)
        recyclerViewList.recycledViewPool.setMaxRecycledViews(adapterShopList.disabledCONST, adapterShopList.max_poolCONST)

        /*долгий клик*/
        setupLongCliclListner()
        /*короткий клик*/
        setupShortCliclListner()
        setupSwipeToDelete(recyclerViewList)
    }

    private fun setupSwipeToDelete(recyclerViewList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapterShopList.shopList[viewHolder.adapterPosition]
                viewModel.deleteItemLD(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerViewList)
    }

    private fun setupShortCliclListner() {
        adapterShopList.onShortClickShopItemListner = {
            Log.d("MainActivityShopItem", "отображение перехода ")
        }
    }

    private fun setupLongCliclListner() {
        adapterShopList.onLongClickShopItemListner = {
            viewModel.editEnanleStateItemLD(it)
        }
    }


}

