package com.example.newsapp.data.remote

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.data.entities.NewsResponse
import com.example.newsapp.db.ArticleDatabase
import retrofit2.Response

class NewsDataSource(private val articleDatabase: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponse =
        RetrofitInstance.api.getHeadlines(country = countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int): NewsResponse =
        RetrofitInstance.api.getNews(searchQuery, pageNumber)

}