package com.annalech.listofproducts.domain

class DeleteShopItemInListUseCase (private val repository: ShopListRepository){
   suspend fun deleteItemInList(shopItem: ShopItem){
        repository.deleteItemInList(shopItem)
    }
}