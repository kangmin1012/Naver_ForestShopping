package org.techtown.forestshopping.viewholder

import android.content.Intent
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import org.techtown.forestshopping.DetailActivity
import org.techtown.forestshopping.data.ShoppingData
import org.techtown.forestshopping.databinding.ItemShoppingBinding
import org.techtown.forestshopping.utill.formatMoney

class ShoppingVH (var binding : ItemShoppingBinding) : RecyclerView.ViewHolder(binding.root){

    fun onBind(data : ShoppingData.Data){
        data.title = HtmlCompat.fromHtml(data.title, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        binding.shopping = data

        data.formatLprice = data.lprice.formatMoney()
        data.formatHprice = data.hprice.formatMoney()

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.putExtra("data", data)

            itemView.context.startActivity(intent)

        }
    }


}