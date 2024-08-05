package com.example.quotegeneratorapp

import androidx.lifecycle.LiveData
import retrofit2.Response

class QuoteRepository(private val dao : FavoriteQuoteDAO) {
    val allFavoriteQuotes: LiveData<List<FavoriteQuote>> = dao.getAllFavoriteQuotes()

    suspend fun insertFavorite(quote: FavoriteQuote) {
        dao.insert(quote)
    }

    suspend fun deleteFavorite(quote: FavoriteQuote) {
        dao.delete(quote)
    }

    suspend fun fetchDailyQuote(): List<Quote> {
        return RetrofitInstance.api.getDailyQuote()
    }
}