package com.example.lexilearn.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class VocabularyWord(
    @PrimaryKey val word: String,
    val translation: String,
    val context: String, // Article snippet where the word was found
    val dateAdded: Long = System.currentTimeMillis(),
    
    // SM-2 Algorithm fields
    val interval: Int = 0,      // Days until next review
    val repetitions: Int = 0,   // Number of successful repetitions
    val easeFactor: Double = 2.5, // E-Factor
    val nextReviewDate: Long = System.currentTimeMillis()
)
