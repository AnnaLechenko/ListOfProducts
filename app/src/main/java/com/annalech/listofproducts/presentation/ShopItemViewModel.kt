package com.annalech.listofproducts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annalech.listofproducts.data.ShopListRepositoryImpl
import com.annalech.listofproducts.domain.AddShopItemUseCase
import com.annalech.listofproducts.domain.EditShopItemUseCase
import com.annalech.listofproducts.domain.GetItemShopListUseCase
import com.annalech.listofproducts.domain.ShopItem

class ShopItemViewModel : ViewModel(){
    private val repository = ShopListRepositoryImpl

    private val _errorInputName_LD = MutableLiveData<Boolean>()
    val errorInputName_LD : LiveData<Boolean>
        get() =  _errorInputName_LD

    private val _errorInputCount_LD = MutableLiveData<Boolean>()
    val errorInputCount_LD : LiveData<Boolean>
        get() =  _errorInputCount_LD



    private val _shouldCloseScreen_LD = MutableLiveData<Unit>()
    val shouldCloseScreen_LD : LiveData<Unit>
        get() = _shouldCloseScreen_LD



    val getItemUseCase = GetItemShopListUseCase(repository)

    val addItemUseCase = AddShopItemUseCase(repository)
    val editItemUseCase = EditShopItemUseCase(repository)


    private val _shopItem_LD = MutableLiveData<ShopItem>()
    val shopItem_LD: LiveData<ShopItem>
        get() = _shopItem_LD

    fun getShopItemInVM(ItemId:Int){
        val item =  getItemUseCase.getShopItem(ItemId)
        _shopItem_LD.value = item

    }

    fun addShopItemInVM(inputName:String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fileValied = validateInput(name,count)
        if (fileValied){
            val newItem = ShopItem(name,count,true)
            addItemUseCase.addItemToTheShopList(newItem)
            finishWork()
        }
    }

    fun editShopItemInVM(inputName:String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fileValied = validateInput(name,count)
        if (fileValied){
            _shopItem_LD.value?.let {
                val oldItem = it.copy(name = name, count = count)
                editItemUseCase.editItemInList(oldItem)
                finishWork() }

        }
    }

    fun parseName(inputName:String?):String{
        return inputName?.trim()?: ""
    }

    fun parseCount(inputCount:String?):Int{
        return try {
            inputCount?.toInt() ?: 0
        } catch (e:Exception){
            0
        }
    }

    private fun validateInput(name: String,count:Int):Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName_LD.value = true
            result = false
        }
        if (count<=0){
            _errorInputCount_LD.value = true
            result =false}
        return result
    }


    public fun resetErrorInputName(){ //метод сброса значение ошибки
        _errorInputName_LD.value = false

    }

    public fun resetErrorInputCount(){//метод сброса значение ошибки
        _errorInputCount_LD.value = false

    }

    private fun finishWork(){
        _shouldCloseScreen_LD.value = Unit
    }


}

