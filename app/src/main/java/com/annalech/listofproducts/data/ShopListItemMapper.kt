package com.annalech.listofproducts.data

import com.annalech.listofproducts.domain.ShopItem
import javax.inject.Inject

class ShopListItemMapper  @Inject constructor(){

    fun mapEntityToDbModel(shopItem: ShopItem):ShopItemDbModel{
        return  ShopItemDbModel(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
        )
    }

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel):ShopItem{
        return  ShopItem(
            id = shopItemDbModel.id,
            name = shopItemDbModel.name,
            count = shopItemDbModel.count,
            enabled = shopItemDbModel.enabled
        )
    }

    fun mapListDbModelToEntite(list: List<ShopItemDbModel>):List<ShopItem>{
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}