package com.annalech.listofproducts.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item_changes)

     //   parseMode()
        Log.d("ShopItemActivity",mode.toString())
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        xmlView()
     //   addTextChangeListner()
     //   laungeMode()
     //   observeViewModel()
    }




private fun  laungeMode(){
        when(screenMode){
            MODE_ADD ->{
                Log.d("ShopItemActivity", "мод добавления")
                launchAddMode()
            }
            MODE_EDIT ->{
                Log.d("ShopItemActivity", "мод добавления")
                launchEditMode()
            }
        }
}
    private  fun addTextChangeListner(){
        inputName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("ShopItemActivity", "имя ошибку ввода")
                viewModel.resetErrorInputName()
            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        inputCount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("ShopItemActivity", "ввод ошибку имя")
                viewModel.resetErrorInputCount()
            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private  fun observeViewModel(){

        viewModel.errorInputCount_LD.observe(this){
            val message = if (it){
                getString(R.string.error_input_count)
            }else{null }
            titleCount.error = message
            Log.d("ShopItemActivity", "подписка на ошибку ввода числа")
        }
        viewModel.inputErrorName_LD.observe(this){
            val message = if (it){
                getString(R.string.error_input_name)
            }else{null }
            titleName.error = message
            Log.d("ShopItemActivity", "подписка на ошибку ввода имени")
        }
        viewModel.shouldCloseScreenLD.observe(this){
            finish()
            Log.d("ShopItemActivity", "закрыта страница добавление ")
        }
    }

    private fun launchEditMode(){
        viewModel.getShopItemInVM(shopItemId)
        Log.d("ShopItemActivity", "получен элемент по ай ди")
        viewModel.shopItem_LD.observe(this){
            inputName.setText(it.name)
            inputCount.setText(it.count.toString())

        }
        buttonSave.setOnClickListener{
            viewModel.editShopItemInVM(inputName.text?.toString(), inputCount.text?.toString())
            Log.d("ShopItemActivity", "добавлен измен элемента в списки при нажатии книпки  - сработад мод и кнопка")
        }
    }


    private fun launchAddMode(){

        buttonSave.setOnClickListener{
            viewModel.addShopItemInVM(inputName.text?.toString(), inputCount.text?.toString())
            Log.d("ShopItemActivity", "добавление в списки при нажатии книпки  - сработад мод и кнопка")
        }
    }


    private fun parseMode(){
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)){
            Log.d("ShopItemActivity", "нет загрузка в моде")
            throw RuntimeException("is absent mode")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode!= MODE_ADD && mode!= MODE_EDIT){
            Log.d("ShopItemActivity", "левая загрузка в моде")
            throw RuntimeException("unknow mode $mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
                Log.d("ShopItemActivity", "не передан ид")
                throw RuntimeException("item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID,ShopItem.UNDEFINED_ID)
            Log.d("ShopItemActivity", "получен ай ди элемента")
        }
    }


 private fun xmlView(){
     titleName   = findViewById(R.id.titleNameLayout)
    titleCount  = findViewById(R.id.titleCountLayout)
     inputName  = findViewById(R.id.textInputName)
    inputCount = findViewById(R.id.textInputCount)
    buttonSave  = findViewById(R.id.save_button)
     Log.d("ShopItemActivity", "Вью элементы видны")
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
            Log.d("ShopItemActivity", "выззван интент добавления с первой активити")
            return int


        }


        fun newIntentEditItem(context: Context, shopItemId: Int):Intent{
            val int = Intent(context, ShopItemActivity::class.java)
            int.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            int.putExtra(EXTRA_SHOP_ITEM_ID,shopItemId)
            Log.d("ShopItemActivity", "выззван интент изменения из первой активити")
            return int
        }


    }

}