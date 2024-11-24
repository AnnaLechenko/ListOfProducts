package com.annalech.listofproducts.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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

        val status = if (item.enabled) {
           "Active"
        } else{
            "Not Active"
        }
        if (item.enabled){
            holder.tvName.text = "${item.name} $status"
            holder.tvCount.text = item.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context,
                android.R.color.holo_red_light))
        }


    }


    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.tvName.text = ""
        holder.tvCount.text = ""
        holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context,
            android.R.color.white))
    }


    class ShopItemViewHolder( val view:View):RecyclerView.ViewHolder(view){

        val tvName = view.findViewById<TextView>(R.id.name_item)
        val tvCount =view.findViewById<TextView>(R.id.item_count)
    }
}