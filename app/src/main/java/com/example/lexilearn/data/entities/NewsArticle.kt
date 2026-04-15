package com.example.lexilearn.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_articles")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val author: String,
    val date: String,
    val language: String,
    val category: String,
    val imageUrl: String? = null
)
