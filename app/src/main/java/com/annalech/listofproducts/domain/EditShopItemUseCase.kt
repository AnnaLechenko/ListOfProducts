package com.annalech.listofproducts.domain

class EditShopItemUseCase(private val repository: ShopListRepository) {
    fun editItemInList(shopItem: ShopItem){
        repository.editItemInList(shopItem)
    }
}