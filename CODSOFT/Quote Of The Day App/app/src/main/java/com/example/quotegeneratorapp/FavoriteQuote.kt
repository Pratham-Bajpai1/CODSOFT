package com.example.quotegeneratorapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_quotes")
data class FavoriteQuote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val author: String
)
