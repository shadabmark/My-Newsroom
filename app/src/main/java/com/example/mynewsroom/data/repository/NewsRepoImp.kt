package com.example.mynewsroom.data.repository

import com.example.mynewsroom.util.utilities.Resource
import com.example.mynewsroom.data.remote.NewsApi
import com.example.mynewsroom.domain.model.Article
import com.example.mynewsroom.domain.repository.NewsRepo
import javax.inject.Inject

class NewsRepoImp @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepo {

    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try {
            val response = newsApi.getBreakingNews(category = category)
            val filteredArticles = response.articles.filter { it.urlToImage?.isNotEmpty() == true }
            Resource.Success(filteredArticles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun searchForNews(query: String): Resource<List<Article>> {
        return try {
            val resource = newsApi.searchForNews(query = query)
            val filteredArticles = resource.articles.filter { it.urlToImage?.isNotEmpty() == true }
            Resource.Success(filteredArticles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}