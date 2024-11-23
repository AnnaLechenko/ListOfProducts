package com.annalech.listofproducts.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
private var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)  //доступ к классу с лив дата
        viewModel.shopListLiveData.observe(this){
            Log.d("ViewModelTest", it.toString())

           if (count == 0 ) {count++
               viewModel.editEnanleStateItemLD(it[2])
               }
        }



    }
}