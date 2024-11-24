package com.annalech.listofproducts.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annalech.listofproducts.R
import com.annalech.listofproducts.domain.ShopItem

class AdapterShopList : RecyclerView.Adapter<AdapterShopList.ShopItemViewHolder>() {



        var  shopList = listOf<ShopItem>()
            set(value){
                field = value
                notifyDataSetChanged()
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_enable,
            parent,
            false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  shopList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]

        holder.tvName.text = item.name
        holder.tvCount.text = item.name
    }


    class ShopItemViewHolder( val view:View):RecyclerView.ViewHolder(view){

        val tvName = view.findViewById<TextView>(R.id.name_item)
        val tvCount =view.findViewById<TextView>(R.id.item_count)
    }
}