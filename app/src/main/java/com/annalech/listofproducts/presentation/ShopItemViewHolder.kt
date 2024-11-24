package com.annalech.listofproducts.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annalech.listofproducts.R

    class ShopItemViewHolder( val view: View): RecyclerView.ViewHolder(view){

        val tvName = view.findViewById<TextView>(R.id.name_item)
        val tvCount =view.findViewById<TextView>(R.id.item_count)
    }
