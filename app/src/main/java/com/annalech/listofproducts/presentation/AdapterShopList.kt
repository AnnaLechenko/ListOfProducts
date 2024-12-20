package com.annalech.listofproducts.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem



class AdapterShopList : ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCollback()) {



        var count = 0

        var onLongClickShopItemListner :((ShopItem) -> Unit)?= null
      var onShortClickShopItemListner : ((ShopItem) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        Log.d("AdapterViewList", "OncreateViewHolder, count : ${++count}")

        val layote = when(viewType) {
            ENABLED -> {R.layout.item_shop_enable}
             DISABLED -> { R.layout.item_shop_disabled}
            else -> throw RuntimeException("Unknown view type in getItemViewType(): $viewType ")
        }

        val view =  LayoutInflater.from(parent.context).inflate(
           layote,
            parent,
            false)
        return ShopItemViewHolder(view)
    }

//    override fun getItemCount(): Int {
//      return  shopList.size
//    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.view.setOnLongClickListener{
            onLongClickShopItemListner?.invoke( item)
            Log.d("MainActivityShopItem", "изменение цвета при нажатии долгом $item $count")
            true
        }

        holder.view.setOnClickListener{
            Log.d("MainActivityShopItem", "Переход на другую страницу при нажатии $item $count")
            onShortClickShopItemListner?.invoke(item)
        }

        if (item.enabled){
            holder.tvName.text = item.name
            holder.tvCount.text = item.count.toString()
            holder.tvName.setTextColor(
                ContextCompat.getColor(holder.view.context,
                    android.R.color.black))
            holder.tvCount.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.black))
        }
        else{
            holder.tvName.text = item.name
            holder.tvCount.text = item.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context,
                android.R.color.white))

        }



    }



    override fun getItemViewType(position: Int): Int {

        val item = getItem(position)
       return if (item.enabled){
            return  ENABLED
        } else{ return  DISABLED }

    }






    companion object{
        const val ENABLED = 101
         const val DISABLED = 100
        const val POOL_MAX_SIZE = 15
    }

    val enabledCONST = ENABLED
    val disabledCONST = DISABLED
    val max_poolCONST = POOL_MAX_SIZE


    interface OnLongClickItemShopListnerIF{
        fun onLongClickShopItem(shopItem: ShopItem)
    }

    interface OnShortClickListnerIF{
        fun onShortClickItemShop(shopItem: ShopItem)
    }

}
