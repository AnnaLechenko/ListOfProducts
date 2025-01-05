package com.annalech.listofproducts.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
   suspend fun addItemToTheShopList(shopItem: ShopItem)
   suspend fun deleteItemInList(shopItem: ShopItem)
   suspend fun   editItemInList(shopItem: ShopItem)
   suspend fun getShopItem(itemId:Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>


}