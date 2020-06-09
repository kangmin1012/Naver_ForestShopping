package org.techtown.forestshopping.utill

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {

    @BindingAdapter("imgUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String){

        Glide.with(imageView.context).load(url).into(imageView)

    }

}