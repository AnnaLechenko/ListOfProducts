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
    val shopListLiveData = MutableLiveData<List<ShopItem>>()

    val useCaseDeleteItem = DeleteShopItemInListUseCase(repository)
    val useCaseGetList = GetListShopListUseCase(repository)
    val useCaseGetItem = EditShopItemUseCase(repository)

    fun getShopList() {
        val list = useCaseGetList.getShopList()
        shopListLiveData.value = list
    }


}