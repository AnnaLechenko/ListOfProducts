package com.annalech.listofproducts.domain

class GetItemShopListUseCase (private val repository: ShopListRepository) {
    fun getShopItem(itemId:Int): ShopItem {

      return repository.getShopItem(itemId)
    }
}