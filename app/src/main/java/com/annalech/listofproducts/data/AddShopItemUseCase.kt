package com.annalech.listofproducts.data

class AddShopItemUseCase (private val repository: ShopListRepository) {
    fun addItemToTheShopList(shopItem: ShopItem){
        repository.addItemToTheShopList(shopItem)
    }
}