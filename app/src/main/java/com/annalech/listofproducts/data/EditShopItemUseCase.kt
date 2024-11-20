package com.annalech.listofproducts.data

class EditShopItemUseCase(private val repository: ShopListRepository) {
    fun editItemInList(shopItem: ShopItem){
        repository.editItemInList(shopItem)
    }
}