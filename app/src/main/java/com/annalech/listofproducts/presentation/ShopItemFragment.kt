package com.annalech.listofproducts.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem
import com.annalech.listofproducts.presentation.ShopItemActivity.Companion

import com.google.android.material.textfield.TextInputLayout


class ShopItemFragment
    :Fragment(){

        private lateinit var onEditingFinishedListner:OnEditingFinishedListner
    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    private lateinit var titleName :TextInputLayout
    private lateinit var titleCount:TextInputLayout
    private lateinit var etName:EditText
    private lateinit var etCount:EditText
    private lateinit var buttonSave:Button

    private lateinit var viewModel: ShopItemViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListner){
            onEditingFinishedListner = context
        } else{
            throw RuntimeException("activity must impliments OnEditingFinishedListner ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        xmlView(view)
        addTextChangeListner()

        when(screenMode){
            MODE_ADD ->{launchAddMode() }
            MODE_EDIT ->{launchEditMode() } }

        observeViewModel()


    }














    private fun observeViewModel(){
        viewModel.errorInputCount_LD.observe(viewLifecycleOwner){
            val message =   if(it){
                getString(R.string.error_input_count)
            } else{
                null
            }
            titleCount.error = message
        }
        viewModel.errorInputName_LD.observe(viewLifecycleOwner){
            val message =   if(it){
                getString(R.string.error_input_name)
            } else{
                null
            }
            titleName.error = message
        }
        viewModel.shouldCloseScreen_LD.observe(viewLifecycleOwner){
           onEditingFinishedListner?.onEditingFinished()
        }
    }

    private fun addTextChangeListner(){
        etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    private fun launchEditMode(){
        viewModel.getShopItemInVM(shopItemId)
        viewModel.shopItem_LD.observe(viewLifecycleOwner){
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        buttonSave.setOnClickListener{
            viewModel.editShopItemInVM(etName.text?.toString(), etCount.text?.toString())
        }
    }
    private fun launchAddMode(){
        buttonSave.setOnClickListener{
            viewModel.addShopItemInVM(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun parseParams(){
        val arg = requireArguments()
        if(!arg.containsKey(SCREEN_MODE)){
            throw RuntimeException("is absent mode")
        }
        val mode = arg.getString(SCREEN_MODE)
        if (mode!=MODE_ADD && mode!= MODE_EDIT){
            throw RuntimeException("unknow mode $mode")
        }
        screenMode = mode

        if (screenMode ==  MODE_EDIT){
            if (!arg.containsKey(EXTRA_SHOP_ITEM_ID)){
                throw RuntimeException("item id is absent")
            }
            shopItemId = arg.getInt(EXTRA_SHOP_ITEM_ID,ShopItem.UNDEFINED_ID)
        }

    }


    private fun xmlView(view: View){
        titleName   = view.findViewById(R.id.titleNameLayout)
        titleCount  = view.findViewById(R.id.titleCountLayout)
        etName  = view.findViewById(R.id.textInputName)
        etCount = view.findViewById(R.id.textInputCount)
        buttonSave  = view.findViewById(R.id.save_button)
    }


    interface OnEditingFinishedListner {
        fun onEditingFinished()
    }


    companion object{
        private const val SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_mode_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""


        fun newInstanseAddItem():ShopItemFragment{

          return  ShopItemFragment().apply {
                arguments =  Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
            //полный варик
//            arg.putString(SCREEN_MODE, MODE_ADD)
//            val fragment = ShopItemFragment()
//            fragment.arguments = arg
//            return  fragment
        }
        fun newInstansEditItem(shopItemId:Int):ShopItemFragment{
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(EXTRA_SHOP_ITEM_ID,shopItemId)
                }
            }
        }
    }}


