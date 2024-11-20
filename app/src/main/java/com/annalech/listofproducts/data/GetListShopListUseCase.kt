package com.annalech.listofproducts.data

class GetListShopListUseCase (private val repository: ShopListRepository){
    fun getShopList(): List<ShopItem>{

        return repository.getShopList()
    }
}