package com.annalech.listofproducts.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annalech.listofproducts.data.ShopListRepositoryImpl
import com.annalech.listofproducts.domain.DeleteShopItemInListUseCase
import com.annalech.listofproducts.domain.EditShopItemUseCase
import com.annalech.listofproducts.domain.GetListShopListUseCase
import com.annalech.listofproducts.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val useCaseDeleteItem : DeleteShopItemInListUseCase,
    val useCaseGetList: GetListShopListUseCase,
    val useCaseEditItem : EditShopItemUseCase

):  ViewModel() {

//    private val scope = CoroutineScope(Dispatchers.IO)   //фоновый поток




    val shopListLiveData = useCaseGetList.getShopList()



    fun deleteItemLD(item: ShopItem){
       viewModelScope.launch {
            useCaseDeleteItem.deleteItemInList(item)
        }

    }

    fun editEnanleStateItemLD(item: ShopItem){
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            useCaseEditItem.editItemInList(newItem)
        }
    }


}