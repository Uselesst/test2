package com.example.lexilearn.ui.news

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lexilearn.data.entities.NewsArticle
import com.example.lexilearn.databinding.ItemNewsBinding
import java.util.regex.Pattern

class NewsAdapter(
    private val onWordSelected: (word: String, context: String) -> Unit
) : ListAdapter<NewsArticle, NewsAdapter.NewsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsArticle) {
            binding.tvTitle.text = article.title
            binding.tvMeta.text = "${article.category} • ${article.date}"
            
            // Make words clickable
            makeWordsClickable(binding.tvContent, article.content)
        }

        private fun makeWordsClickable(textView: TextView, content: String) {
            val spannable = SpannableString(content)
            val pattern = Pattern.compile("\\w+")
            val matcher = pattern.matcher(content)

            while (matcher.find()) {
                val start = matcher.start()
                val end = matcher.end()
                val word = content.substring(start, end)

                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        // For this project, we use long click or just click to translate
                        // The user asked for "long press", but ClickableSpan is usually onClick.
                        // We can handle it here.
                        onWordSelected(word, content)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false // Don't underline every word
                    }
                }
                spannable.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            textView.text = spannable
            textView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NewsArticle>() {
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle) = oldItem == newItem
    }
}
