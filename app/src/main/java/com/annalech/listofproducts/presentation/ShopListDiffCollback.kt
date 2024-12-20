package com.annalech.listofproducts.presentation

import androidx.recyclerview.widget.DiffUtil
import com.annalech.listofproducts.domain.ShopItem

class ShopListDiffCollback (
    private val oldList: List<ShopItem>,
    private val newList:List<ShopItem>
):DiffUtil.Callback(){

    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem=  oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem=  oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem==newItem
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}