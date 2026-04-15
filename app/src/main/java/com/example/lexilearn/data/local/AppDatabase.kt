package com.example.lexilearn.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lexilearn.data.entities.NewsArticle
import com.example.lexilearn.data.entities.VocabularyWord

@Database(entities = [NewsArticle::class, VocabularyWord::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
}
