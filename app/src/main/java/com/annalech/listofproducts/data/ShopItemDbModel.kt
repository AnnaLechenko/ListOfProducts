package com.annalech.listofproducts.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.annalech.listofproducts.domain.ShopItem.Companion.UNDEFINED_ID

@Entity(tableName = "shop_item_tabl" )
data class ShopItemDbModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int ,
    val name: String,
    val count: Int,
    val enabled: Boolean){


}