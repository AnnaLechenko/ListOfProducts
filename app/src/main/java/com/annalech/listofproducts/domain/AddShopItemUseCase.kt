package com.annalech.listofproducts.domain

class AddShopItemUseCase (private val repository: ShopListRepository) {
    fun addItemToTheShopList(shopItem: ShopItem){
        repository.addItemToTheShopList(shopItem)
    }
}