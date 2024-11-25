package com.annalech.listofproducts.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.annalech.listofproducts.domain.ShopItem
import com.annalech.listofproducts.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {


//    private val shopList = mutableListOf<ShopItem>()
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    private val shopList = sortedSetOf<ShopItem>(Comparator { o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0

init {
    for(i in 0 until 50){
        addItemToTheShopList(ShopItem("Name $i", i, Random.nextBoolean()))
    }
}


    override fun addItemToTheShopList(shopItem: ShopItem) {
        if (shopItem.id== ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateListLD()
    }

    override fun deleteItemInList(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateListLD()
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

    override fun getShopList(): LiveData<List<ShopItem> >{
        return shopListLD
    }


    private fun updateListLD(){
        shopListLD.value = shopList.toList()
    }
}