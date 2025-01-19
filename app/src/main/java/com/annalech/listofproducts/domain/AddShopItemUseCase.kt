package com.annalech.listofproducts.domain

import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(
    private val repository: ShopListRepository
) {
    suspend fun addItemToTheShopList(shopItem: ShopItem){
        repository.addItemToTheShopList(shopItem)
    }
}