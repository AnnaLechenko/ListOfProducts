package com.annalech.listofproducts.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var titleName :TextInputLayout
//    private lateinit var titleCount:TextInputLayout
//    private lateinit var etName:EditText
//    private lateinit var etCount:EditText
//    private lateinit var buttonSave:Button
//
//    private lateinit var viewModel: ShopItemViewModel
    private var screenMode = MODE_UNKNOW
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_item_container)



//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        Log.d("ShopItemActivity",mode.toString())

        parseIntent()
//        Log.d("ShopItemActivity", "parse intent suscess")
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        xmlView()
//        addTextChangeListner()

     launchRighMode()

//        observeViewModel()
//
//
//
//


    }


    private fun launchRighMode(){
       val fragment =  when(screenMode){
            MODE_ADD ->{ ShopItemFragment.newInstanseAddItem() }
            MODE_EDIT ->{ShopItemFragment.newInstansEditItem(shopItemId)}
           else -> throw RuntimeException("unknow mode  in launchRighMode(")
        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

//    private fun observeViewModel(){
//        viewModel.errorInputCount_LD.observe(this){
//            val message =   if(it){
//                getString(R.string.error_input_count)
//            } else{
//                null
//            }
//            titleCount.error = message
//        }
//        viewModel.errorInputName_LD.observe(this){
//            val message =   if(it){
//                getString(R.string.error_input_name)
//            } else{
//                null
//            }
//            titleName.error = message
//        }
//        viewModel.shouldCloseScreen_LD.observe(this){
//            finish()
//        }
//    }
//
//    private fun addTextChangeListner(){
//        etName.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//        })
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputCount()
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//    }
//
//
//    private fun launchEditMode(){
//        viewModel.getShopItemInVM(shopItemId)
//        viewModel.shopItem_LD.observe(this){
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        buttonSave.setOnClickListener{
//            viewModel.editShopItemInVM(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//    private fun launchAddMode(){
//        buttonSave.setOnClickListener{
//            viewModel.addShopItemInVM(etName.text?.toString(), etCount.text?.toString())
//        }
//    }

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


//    private fun xmlView(){
//        titleName   = findViewById(R.id.titleNameLayout)
//        titleCount  = findViewById(R.id.titleCountLayout)
//        etName  = findViewById(R.id.textInputName)
//        etCount = findViewById(R.id.textInputCount)
//        buttonSave  = findViewById(R.id.save_button)
//    }


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