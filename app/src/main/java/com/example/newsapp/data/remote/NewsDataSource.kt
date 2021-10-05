package com.example.newsapp.data.remote

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase

class NewsDataSource(articleDatabase: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int){
        RetrofitInstance.api.getHeadlines(country = countryCode, pageNumber)
    }
}