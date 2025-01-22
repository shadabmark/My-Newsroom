package com.example.mynewsroom.presentation_layer.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsroom.util.utilities.Resource
import com.example.mynewsroom.domain.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : ViewModel() {

    var state by mutableStateOf(NewsScreenState())
    private var searchJob: Job? = null

    fun onEvent(event: NewsScreenEvent) {
        when (event) {
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(state.category)
            }
            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
                getNewsArticles(category = state.category)
            }
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    article = emptyList()
                )
            }
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000)
                    searchForNews(query = state.searchQuery)
                }
            }
        }
    }

    private fun getNewsArticles(category: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when (val result = newsRepo.getTopHeadlines(category = category)) {
                is Resource.Success -> {
                    state = state.copy(
                        article = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        error = result.message,
                        isLoading = false,
                        article = emptyList()
                    )
                }
            }
        }
    }

    private fun searchForNews(query: String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when (val result = newsRepo.searchForNews(query = query)) {
                is Resource.Error -> {
                    state = state.copy(
                        error = result.message,
                        isLoading = false,
                        article = emptyList()
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        article = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }
}