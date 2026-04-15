package com.example.lexilearn.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexilearn.data.entities.NewsArticle
import com.example.lexilearn.data.entities.VocabularyWord
import com.example.lexilearn.data.local.VocabularyDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val vocabularyDao: VocabularyDao
) : ViewModel() {

    private val _news = MutableStateFlow<List<NewsArticle>>(emptyList())
    val news: StateFlow<List<NewsArticle>> = _news

    init {
        loadMockNews()
    }

    private fun loadMockNews() {
        _news.value = listOf(
            NewsArticle(
                title = "The Future of AI in Mobile Development",
                content = "Artificial intelligence is revolutionizing how we build mobile applications. Developers are now using large language models to automate coding tasks and improve user experiences through personalized content and intuitive interfaces.",
                author = "Tech Insider",
                date = "15 April 2026",
                language = "English",
                category = "Technology"
            ),
            NewsArticle(
                title = "Kotlin Multiplatform Reaches Stable",
                content = "JetBrains has announced that Kotlin Multiplatform is now stable and ready for production use. This milestone allows developers to share business logic across Android, iOS, web, and desktop platforms while maintaining native performance and UI capabilities.",
                author = "Kotlin Team",
                date = "14 April 2026",
                language = "English",
                category = "Development"
            )
        )
    }

    fun addWordToVocabulary(word: String, translation: String, context: String) {
        viewModelScope.launch {
            val vocabularyWord = VocabularyWord(
                word = word,
                translation = translation,
                context = context
            )
            vocabularyDao.insertWord(vocabularyWord)
        }
    }
}
