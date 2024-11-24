package com.annalech.listofproducts.presentation

import androidx.recyclerview.widget.DiffUtil
import com.annalech.listofproducts.domain.ShopItem

class ShopItemDiffCollback :DiffUtil.ItemCallback<ShopItem>(){

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return  oldItem.id==newItem.id
    }

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return  oldItem==newItem
    }
}