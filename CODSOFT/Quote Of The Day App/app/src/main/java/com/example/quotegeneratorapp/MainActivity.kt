package com.example.quotegeneratorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var quoteTextView: TextView
    private lateinit var favoriteButton: Button
    private lateinit var shareButton: ImageButton
    private lateinit var viewFavoritesButton: Button

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteTextView = findViewById(R.id.quoteTextView)
        favoriteButton = findViewById(R.id.favoriteButton)
        shareButton = findViewById(R.id.shareButton)
        viewFavoritesButton = findViewById(R.id.viewFavoritesButton)

        quoteViewModel.fetchDailyQuote()

        quoteViewModel.dailyQuote.observe(this, Observer { quote ->
            quoteTextView.text = "\"${quote.q}\" - ${quote.a}"
        })

        favoriteButton.setOnClickListener {
            val quoteText = quoteTextView.text.toString()
            val quote = FavoriteQuote(text = quoteText, author = quoteViewModel.dailyQuote.value?.a ?: "Unknown")
            quoteViewModel.insertFavorite(quote)
            Toast.makeText(this, "Quote added to favorites", Toast.LENGTH_SHORT).show()
        }

        shareButton.setOnClickListener {
            shareQuote(quoteTextView.text.toString())
        }

        viewFavoritesButton.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    private fun shareQuote(quote: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, quote)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}