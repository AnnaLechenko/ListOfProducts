package com.annalech.listofproducts.domain

class DeleteShopItemInListUseCase (private val repository: ShopListRepository){
    fun deleteItemInList(shopItem: ShopItem){
        repository.deleteItemInList(shopItem)
    }
}