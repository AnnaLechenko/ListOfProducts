package com.annalech.listofproducts.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.annalech.listofproducts.R
import com.annalech.listofproducts.databinding.ItemShopDisabledBinding
import com.annalech.listofproducts.databinding.ItemShopEnableBinding
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

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layote,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

//    override fun getItemCount(): Int {
//      return  shopList.size
//    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        val binding = holder.binding

        binding.root.setOnLongClickListener{
            onLongClickShopItemListner?.invoke( shopItem)
            Log.d("MainActivityShopItem", "изменение цвета при нажатии долгом $shopItem $count")
            true
        }

        binding.root.setOnClickListener{
            Log.d("MainActivityShopItem", "Переход на другую страницу при нажатии $shopItem $count")
            onShortClickShopItemListner?.invoke(shopItem)
        }


        when(binding){
            is ItemShopDisabledBinding ->{
                binding.shopItem = shopItem
            }
            is ItemShopEnableBinding ->{
                binding.shopItem = shopItem
            }
        }

//        if (item.enabled){
//            binding.tvName.text = item.name
//            binding.tvCount.text = item.count.toString()
//            holder.tvName.setTextColor(
//                ContextCompat.getColor(holder.view.context,
//                    android.R.color.black))
//            holder.tvCount.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.black))
//        }
//        else{






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
