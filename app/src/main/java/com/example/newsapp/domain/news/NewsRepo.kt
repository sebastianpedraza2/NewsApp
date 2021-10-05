package com.example.newsapp.domain.news

import com.example.newsapp.data.entities.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun fetchBreakingNews(countryCode: String, pageNumber: Int): NewsResponse

    suspend fun searchNews(searchQuery: String, pageNumber: Int): NewsResponse
}