package com.annalech.listofproducts.domain

import javax.inject.Inject

class EditShopItemUseCase @Inject constructor(
    private val repository: ShopListRepository
) {
    suspend fun editItemInList(shopItem: ShopItem){
        repository.editItemInList(shopItem)
    }
}