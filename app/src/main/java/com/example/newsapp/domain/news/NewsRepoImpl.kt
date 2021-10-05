package com.example.newsapp.domain.news

import com.example.newsapp.data.entities.NewsResponse
import com.example.newsapp.data.remote.NewsDataSource
import retrofit2.Response

class NewsRepoImpl(private val newsDataSource: NewsDataSource) : NewsRepository {
    override suspend fun fetchBreakingNews(countryCode: String, pageNumber: Int): NewsResponse = newsDataSource.getBreakingNews(countryCode, pageNumber)

}