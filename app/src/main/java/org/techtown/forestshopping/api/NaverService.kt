package org.techtown.forestshopping.api

import org.techtown.forestshopping.data.ShoppingData
import retrofit2.Call
import retrofit2.http.*

interface NaverService {

    @GET("search/shop.json")
    fun getSearchShopping(
        @Query("query") query : String,
        @Query("sort") sort : String = "sim"
    ) : Call<ShoppingData>
}