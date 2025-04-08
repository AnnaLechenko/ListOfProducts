package com.annalech.listofproducts.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.annalech.listofproducts.domain.ShopItem
import com.annalech.listofproducts.domain.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    private val shopListDao : ShopListDao,
      private val mapper :ShopListItemMapper
) : ShopListRepository {

    override suspend fun addItemToTheShopList(shopItem: ShopItem) {
       shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteItemInList(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editItemInList(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(itemId: Int): ShopItem {
      val dbModel  = shopListDao.getShopItem(shopItemId = itemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem> >{
        return shopListDao.getShopList().map {
            mapper.mapListDbModelToEntite(it)
        }
    }


}