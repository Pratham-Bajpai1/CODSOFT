package com.example.quotegeneratorapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels

class FavoritesActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteQuotesAdapter

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recyclerView = findViewById(R.id.favoritesRecyclerView) // Ensure this ID matches your XML
        adapter = FavoriteQuotesAdapter { quote -> deleteFavoriteQuote(quote) }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        quoteViewModel.allFavoriteQuotes.observe(this, Observer { quotes ->
            quotes?.let { adapter.submitList(it) }
        })
    }

    private fun deleteFavoriteQuote(quote: FavoriteQuote) {
        quoteViewModel.deleteFavorite(quote)
        Toast.makeText(this, "Quote removed from favorites", Toast.LENGTH_SHORT).show()
    }
}