package com.annalech.listofproducts.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addItemToTheShopList(shopItem: ShopItem)
    fun deleteItemInList(shopItem: ShopItem)
    fun   editItemInList(shopItem: ShopItem)
    fun getShopItem(itemId:Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>


}