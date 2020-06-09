package org.techtown.forestshopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.forestshopping.data.ShoppingData
import org.techtown.forestshopping.databinding.ItemShoppingBinding
import org.techtown.forestshopping.databinding.ItemShoppingLinearBinding
import org.techtown.forestshopping.viewholder.ShoppingLinVH
import org.techtown.forestshopping.viewholder.ShoppingVH

class ShoppingAdapter (private val context : Context, private val LAYOUT_CODE : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var data = listOf<ShoppingData.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(LAYOUT_CODE == 0) {
            val binding: ItemShoppingBinding = ItemShoppingBinding.inflate(
                LayoutInflater.from(context),
                parent, false
            )
            ShoppingVH(binding)
        } else {
            val binding: ItemShoppingLinearBinding = ItemShoppingLinearBinding
                .inflate(LayoutInflater.from(context), parent, false)
            ShoppingLinVH(binding)
        }

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShoppingVH ->{
                holder.onBind(data[position])
            }

            is ShoppingLinVH ->{
                holder.onBind(data[position])
            }


        }
    }
}