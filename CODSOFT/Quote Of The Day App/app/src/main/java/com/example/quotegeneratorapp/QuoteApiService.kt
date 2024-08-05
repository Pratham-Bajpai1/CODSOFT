package com.example.quotegeneratorapp

import retrofit2.http.GET

interface QuoteApiService {
    @GET("today")
    suspend fun getDailyQuote(): List<Quote>
}