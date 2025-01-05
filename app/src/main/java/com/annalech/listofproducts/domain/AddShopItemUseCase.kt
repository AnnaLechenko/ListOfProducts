package com.annalech.listofproducts.domain

class AddShopItemUseCase (private val repository: ShopListRepository) {
    suspend fun addItemToTheShopList(shopItem: ShopItem){
        repository.addItemToTheShopList(shopItem)
    }
}