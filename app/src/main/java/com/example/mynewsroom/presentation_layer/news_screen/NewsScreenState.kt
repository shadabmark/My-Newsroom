package com.example.mynewsroom.presentation_layer.news_screen

import com.example.mynewsroom.domain.model.Article

data class NewsScreenState(
    val isLoading: Boolean = false,
    val article: List<Article> = emptyList(),
    val error: String? = null,
    val isSearchBarVisible: Boolean = false,
    val selectedArticle: Article? = null,
    val category: String = "General",
    val searchQuery: String = ""
)