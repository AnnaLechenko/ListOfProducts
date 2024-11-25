package com.annalech.listofproducts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annalech.listofproducts.data.ShopListRepositoryImpl
import com.annalech.listofproducts.domain.DeleteShopItemInListUseCase
import com.annalech.listofproducts.domain.EditShopItemUseCase
import com.annalech.listofproducts.domain.GetListShopListUseCase
import com.annalech.listofproducts.domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl


    val useCaseDeleteItem = DeleteShopItemInListUseCase(repository)
    val useCaseGetList = GetListShopListUseCase(repository)
    val useCaseEditItem = EditShopItemUseCase(repository)

    val shopListLiveData = useCaseGetList.getShopList()



    fun deleteItemLD(item: ShopItem){
        useCaseDeleteItem.deleteItemInList(item)
    }

    fun editEnanleStateItemLD(item: ShopItem){
        val newItem = item.copy(enabled = !item.enabled)
        useCaseEditItem.editItemInList(newItem)
    }


}