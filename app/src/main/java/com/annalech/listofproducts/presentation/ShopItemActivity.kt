package com.annalech.listofproducts.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {

    private lateinit var titleName :TextInputLayout
    private lateinit var titleCount:TextInputLayout
    private lateinit var inputName:EditText
    private lateinit var inputCount:EditText
    private lateinit var buttonSave:Button

    private lateinit var viewModel: ShopItemViewModel
    private var screenMode = MODE_UNKNOW
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item_changes)

        parseMode()

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("ShopItemActivity",mode.toString())

        xmlView()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]


        when(screenMode){
            MODE_ADD ->{launchAddMode()
            }
            MODE_EDIT ->{ launchEditMode()

            }
        }






    }


    private fun launchEditMode(){}
    private fun launchAddMode(){}

    private fun parseMode(){
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("is absent mode")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode!= MODE_ADD && mode!= MODE_EDIT){
            throw RuntimeException("unknow mode $mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
                throw RuntimeException("item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID,ShopItem.UNDEFINED_ID)
        }
    }


 private fun xmlView(){
     titleName   = findViewById(R.id.titleNameLayout)
    titleCount  = findViewById(R.id.titleCountLayout)
     inputName  = findViewById(R.id.textInputName)
    inputCount = findViewById(R.id.textInputCount)
    buttonSave  = findViewById(R.id.save_button)
 }


    companion object{
       private const val EXTRA_SCREEN_MODE = "extra_mode"
       private const val EXTRA_SHOP_ITEM_ID = "extra_mode_id"
       private const val MODE_ADD = "mode_add"
       private const val MODE_EDIT = "mode_edit"
private const val MODE_UNKNOW = ""

        fun newIntentAddItem(context: Context):Intent{
            val int = Intent(context, ShopItemActivity::class.java)
            int.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return int
        }


        fun newIntentEditItem(context: Context, shopItemId: Int):Intent{
            val int = Intent(context, ShopItemActivity::class.java)
            int.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            int.putExtra(EXTRA_SHOP_ITEM_ID,shopItemId)
            return int
        }


    }

}