package com.annalech.listofproducts.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {


    private var screenMode = MODE_UNKNOW
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_item_container)


        parseIntent()

        if(savedInstanceState==null){ //если не было ранее запущено активити онКреейт -то выполняеся блок выбоора фрагмента
            launchRighMode()
        }

    }


    private fun launchRighMode(){
       val fragment =  when(screenMode){
            MODE_ADD ->{ ShopItemFragment.newInstanseAddItem() }
            MODE_EDIT ->{ShopItemFragment.newInstansEditItem(shopItemId)}
           else -> throw RuntimeException("unknow mode  in launchRighMode(")
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .commit()
    }

    private fun parseIntent(){
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