package com.example.lexilearn.data.local

import androidx.room.*
import com.example.lexilearn.data.entities.VocabularyWord
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary ORDER BY dateAdded DESC")
    fun getAllWords(): Flow<List<VocabularyWord>>

    @Query("SELECT * FROM vocabulary WHERE nextReviewDate <= :currentTime")
    fun getWordsForReview(currentTime: Long): Flow<List<VocabularyWord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: VocabularyWord)

    @Delete
    suspend fun deleteWord(word: VocabularyWord)

    @Query("SELECT * FROM vocabulary WHERE word = :word LIMIT 1")
    suspend fun getWord(word: String): VocabularyWord?
}
