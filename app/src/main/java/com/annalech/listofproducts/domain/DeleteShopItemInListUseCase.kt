package com.annalech.listofproducts.domain

import javax.inject.Inject

class DeleteShopItemInListUseCase @Inject constructor(
    private val repository: ShopListRepository){
   suspend fun deleteItemInList(shopItem: ShopItem){
        repository.deleteItemInList(shopItem)
    }
}