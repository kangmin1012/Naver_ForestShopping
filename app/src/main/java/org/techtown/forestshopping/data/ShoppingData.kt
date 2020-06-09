package org.techtown.forestshopping.data

import java.io.Serializable


data class ShoppingData(
    val `items` : List<Data>
){
    data class Data (
        var title : String,
        val link : String,
        val image : String,
        val lprice : Int,
        val hprice : Int,
        val mallName : String,
        val brand : String,
        var formatLprice : String = "0",
        var formatHprice : String = "0"
    ) : Serializable
}