package com.annalech.listofproducts.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.annalech.listofproducts.R

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListner{

    private lateinit var viewModel: MainViewModel
    private lateinit var adapterShopList: AdapterShopList
    private var shopItemContainer: FragmentContainerView? = null

    private var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        shopItemContainer = findViewById(R.id.shop_item_container)

        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)  //доступ к классу с лив дата

        viewModel.shopListLiveData.observe(this){
            Log.d("ViewModelTest", it.toString())
            adapterShopList.submitList(it)
        }

        val  buttunAddItem = findViewById<FloatingActionButton>(R.id.button_add_item)
        buttunAddItem.setOnClickListener{
            if(windowOrientationVertical()){
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else{
                launchFragment(ShopItemFragment.newInstanseAddItem())





            }

        }


    }

    private fun windowOrientationVertical():Boolean{
        return shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container,fragment)
            .addToBackStack(null)
            .commit()
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
        /*удаление свайпом*/
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
                val item = adapterShopList.currentList[viewHolder.adapterPosition]
                viewModel.deleteItemLD(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerViewList)
    }

    private fun setupShortCliclListner() {
                adapterShopList.onShortClickShopItemListner = {
                    if (windowOrientationVertical()){
                            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                            Log.d("ShopItemActivity", "передан из 1 активит во 2 ай ди")
                            startActivity(intent)
                            Log.d("MainActivityShopItem", "отображение перехода ")
                }
                    else{
                        launchFragment(ShopItemFragment.newInstansEditItem(it.id))
                    }


        }
    }

    private fun setupLongCliclListner() {
        adapterShopList.onLongClickShopItemListner = {
            viewModel.editEnanleStateItemLD(it)
            Log.d("MainActivityShopItem", "удержание кнопки")
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this@MainActivity,"Sucsess", Toast.LENGTH_LONG).show()
        supportFragmentManager.popBackStack()
    }


}

