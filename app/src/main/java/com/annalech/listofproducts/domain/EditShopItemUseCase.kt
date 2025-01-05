package com.annalech.listofproducts.domain

class EditShopItemUseCase(private val repository: ShopListRepository) {
    suspend fun editItemInList(shopItem: ShopItem){
        repository.editItemInList(shopItem)
    }
}