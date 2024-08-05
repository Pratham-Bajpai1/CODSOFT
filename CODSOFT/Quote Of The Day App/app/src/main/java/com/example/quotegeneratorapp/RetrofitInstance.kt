package com.example.quotegeneratorapp

import com.example.quotegeneratorapp.QuoteApiService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "https://zenquotes.io/api/" // Update this to your actual API base URL

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: QuoteApiService by lazy {
        retrofit.create(QuoteApiService::class.java)
    }
}