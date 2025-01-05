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
import com.annalech.listofproducts.databinding.ActivityMainBinding

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListner{

    private lateinit var viewModel: MainViewModel
    private lateinit var adapterShopList: AdapterShopList

    private lateinit var binding: ActivityMainBinding

    private var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)  //доступ к классу с лив дата

        viewModel.shopListLiveData.observe(this){
            Log.d("ViewModelTest", it.toString())
            adapterShopList.submitList(it)
        }


        binding.buttonAddItem.setOnClickListener{
            if(windowOrientationVertical()){
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else{
                launchFragment(ShopItemFragment.newInstanseAddItem())





            }

        }


    }

    private fun windowOrientationVertical():Boolean{
        return  binding.shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container,fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView(){
        with(binding.recyclerViewShopList){
            adapterShopList = AdapterShopList()
            adapter = adapterShopList

            recycledViewPool.setMaxRecycledViews(adapterShopList.enabledCONST, adapterShopList.max_poolCONST)
            recycledViewPool.setMaxRecycledViews(adapterShopList.disabledCONST, adapterShopList.max_poolCONST)

        /*долгий клик*/
        setupLongCliclListner()
        /*короткий клик*/
        setupShortCliclListner()
        /*удаление свайпом*/
        setupSwipeToDelete(binding.recyclerViewShopList)
    }}

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

