package com.annalech.listofproducts.domain

import javax.inject.Inject

class GetItemShopListUseCase @Inject constructor(
    private val repository: ShopListRepository
) {
    suspend  fun getShopItem(itemId:Int): ShopItem {

      return repository.getShopItem(itemId)
    }
}