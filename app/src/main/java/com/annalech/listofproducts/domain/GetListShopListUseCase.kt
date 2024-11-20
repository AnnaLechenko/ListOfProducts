package com.annalech.listofproducts.domain

class GetListShopListUseCase (private val repository: ShopListRepository){
    fun getShopList(): List<ShopItem>{

        return repository.getShopList()
    }
}