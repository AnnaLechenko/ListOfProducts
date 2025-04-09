package com.annalech.listofproducts.data

import android.database.Cursor
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

    @Query("SELECT * FROM shop_item_tabl ")
    fun getShopListCursor(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addShopItemProvide(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_item_tabl WHERE id=:shopItemId")
     fun deleteShopItemSync(shopItemId:Int) : Int

    @Query("DELETE FROM shop_item_tabl WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId:Int)

    @Query("SELECT * FROM shop_item_tabl WHERE id=:shopItemId LIMIT 1")
   suspend fun getShopItem(shopItemId: Int): ShopItemDbModel

}