package com.example.quotegeneratorapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: QuoteRepository

    val allFavoriteQuotes: LiveData<List<FavoriteQuote>>

    private val _dailyQuote = MutableLiveData<Quote>()
    val dailyQuote: LiveData<Quote> get() = _dailyQuote

    init {
        val dao = QuoteDatabase.getDatabase(application).favoriteQuoteDao()
        repository = QuoteRepository(dao)
        allFavoriteQuotes = repository.allFavoriteQuotes
    }

    fun insertFavorite(quote: FavoriteQuote) = viewModelScope.launch {
        repository.insertFavorite(quote)
    }

    fun deleteFavorite(quote: FavoriteQuote) = viewModelScope.launch {
        repository.deleteFavorite(quote)
    }

    fun fetchDailyQuote() = viewModelScope.launch {
        try {
            val quotes = repository.fetchDailyQuote()
            if (quotes.isNotEmpty()) {
                _dailyQuote.postValue(quotes[0]) // Take the first quote from the list
            }
        } catch (e: Exception) {
            // Handle the error appropriately
            e.printStackTrace()
        }
    }
}