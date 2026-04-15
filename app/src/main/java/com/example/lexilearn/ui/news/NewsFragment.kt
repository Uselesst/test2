package com.example.lexilearn.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lexilearn.R
import com.example.lexilearn.databinding.BottomSheetTranslationBinding
import com.example.lexilearn.databinding.FragmentNewsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter { word, context ->
            showTranslationBottomSheet(word, context)
        }
        binding.rvNews.adapter = adapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collect { newsList ->
                    adapter.submitList(newsList)
                }
            }
        }
    }

    private fun showTranslationBottomSheet(word: String, context: String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetTranslationBinding.inflate(layoutInflater)
        
        bottomSheetBinding.tvSelectedWord.text = word
        
        // Mock translation logic
        val translation = getMockTranslation(word)
        bottomSheetBinding.tvTranslation.text = translation

        bottomSheetBinding.btnAdd.setOnClickListener {
            viewModel.addWordToVocabulary(word, translation, context)
            Toast.makeText(requireContext(), R.string.word_added, Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.show()
    }

    private fun getMockTranslation(word: String): String {
        return when (word.lowercase()) {
            "intelligence" -> "интеллект"
            "mobile" -> "мобильный"
            "developers" -> "разработчики"
            "language" -> "язык"
            "models" -> "модели"
            "stable" -> "стабильный"
            "production" -> "продакшн"
            "logic" -> "логика"
            else -> "перевод для '$word'"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
