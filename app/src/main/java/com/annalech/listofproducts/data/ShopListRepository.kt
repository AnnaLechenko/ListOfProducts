package com.annalech.listofproducts.data

interface ShopListRepository {
    fun addItemToTheShopList(shopItem: ShopItem)
    fun deleteItemInList(shopItem: ShopItem)
    fun   editItemInList(shopItem: ShopItem)
    fun getShopItem(itemId:Int):ShopItem
    fun getShopList(): List<ShopItem>


}