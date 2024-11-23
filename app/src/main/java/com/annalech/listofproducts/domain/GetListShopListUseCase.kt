package com.annalech.listofproducts.domain

import androidx.lifecycle.LiveData

class GetListShopListUseCase (private val repository: ShopListRepository){
    fun getShopList(): LiveData<List<ShopItem>> {

        return repository.getShopList()
    }
}