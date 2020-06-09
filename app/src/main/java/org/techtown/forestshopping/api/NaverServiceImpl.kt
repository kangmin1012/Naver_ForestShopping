package org.techtown.forestshopping.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.techtown.forestshopping.data.Client
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverServiceImpl {
    private const val BASE_URL = "https://openapi.naver.com/v1/"

    private val interceptor = Interceptor { chain ->
        val newRequest : Request = chain.request().newBuilder().addHeader("X-Naver-Client-Id", Client.OAUTH_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", Client.OAUTH_CLIENT_SECRET)
            .build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder().apply {
        interceptors().add(interceptor)
    }.build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service : NaverService = retrofit.create(NaverService::class.java)
}