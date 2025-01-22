package com.example.mynewsroom.domain.repository

import com.example.mynewsroom.util.utilities.Resource
import com.example.mynewsroom.domain.model.Article

interface NewsRepo {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>

    suspend fun searchForNews(
        query: String
    ): Resource<List<Article>>
}