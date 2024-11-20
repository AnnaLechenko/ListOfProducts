package com.annalech.listofproducts.data

import com.annalech.listofproducts.domain.ShopItem
import com.annalech.listofproducts.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {


    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0



    override fun addItemToTheShopList(shopItem: ShopItem) {
        if (shopItem.id== ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteItemInList(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editItemInList(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopList.remove(oldItem)
        addItemToTheShopList(shopItem)

    }

    override fun getShopItem(itemId: Int): ShopItem {
       return shopList.find {
           it -> it.id==itemId
       } ?: throw Exception("Element with id ${itemId} not found")
    }

    override fun getShopList(): List<ShopItem> {
        return  shopList.toList()
    }

}