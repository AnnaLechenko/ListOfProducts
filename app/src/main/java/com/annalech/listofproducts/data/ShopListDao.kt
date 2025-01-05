package com.annalech.listofproducts.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.annalech.listofproducts.domain.ShopItem

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_item_tabl ")
      fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_item_tabl WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId:Int)

    @Query("SELECT * FROM shop_item_tabl WHERE id=:shopItemId LIMIT 1")
   suspend fun getShopItem(shopItemId: Int): ShopItemDbModel

}