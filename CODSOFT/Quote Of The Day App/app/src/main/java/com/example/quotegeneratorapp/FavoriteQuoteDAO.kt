package com.example.quotegeneratorapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteQuoteDAO {
    @Insert
    suspend fun insert(quote: FavoriteQuote)

    @Delete
    suspend fun delete(quote: FavoriteQuote)

    @Query("SELECT * FROM favorite_quotes")
    fun getAllFavoriteQuotes(): LiveData<List<FavoriteQuote>>
}