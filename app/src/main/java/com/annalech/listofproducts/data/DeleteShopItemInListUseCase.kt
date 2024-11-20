package com.annalech.listofproducts.data

class DeleteShopItemInListUseCase (private val repository: ShopListRepository){
    fun deleteItemInList(shopItem: ShopItem){
        repository.deleteItemInList(shopItem)
    }
}