package com.annalech.listofproducts.presentation

import android.support.v4.os.IResultReceiver._Parcel
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

   private val _NameError_LD = MutableLiveData<Boolean>()
       val NameError_LD : LiveData<Boolean>
           get() =  _NameError_LD

    private val _CountError_LD = MutableLiveData<Boolean>()
    val CountError_LD : LiveData<Boolean>
        get() =  _NameError_LD



    private val _restartLD = MutableLiveData<Unit>()
    val restartLD : LiveData<Unit>
        get() = _restartLD



    val getItemUseCase = GetItemShopListUseCase(repository)

    val addItemUseCase = AddShopItemUseCase(repository)
    val editItemUseCase = EditShopItemUseCase(repository)


    private val _ShopItem_LD = MutableLiveData<ShopItem>()
    val ShopItem_LD: LiveData<ShopItem>
        get() = _ShopItem_LD

    fun getShopItemInVM(ItemId:Int){
       val item =  getItemUseCase.getShopItem(ItemId)
        _ShopItem_LD.value = item

    }

    fun addShopItemInVM(inputName:String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fileValied = validateInput(name,count)
       if (fileValied){
           val newItem = ShopItem(name,count,true)
           addItemUseCase.addItemToTheShopList(newItem)
           finishCode()
       }
    }

    fun editShopItemInVM(inputName:String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fileValied = validateInput(name,count)
        if (fileValied){
         _ShopItem_LD.value?.let {
             val oldItem = it.copy(name = name, count = count)
             editItemUseCase.editItemInList(it)
             finishCode() }

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
            _NameError_LD.value = true
            result = false
        }
        if (count<=0){
            _CountError_LD.value = true
            result =false}
    return result
        }


    public fun resetErrorInputName(){ //метод сброса значение ошибки
        _NameError_LD.value = false

    }

    public fun resetErrorInputCount(){//метод сброса значение ошибки
        _CountError_LD.value = false

    }

    private fun finishCode(){
        _restartLD.value = Unit
    }


}
