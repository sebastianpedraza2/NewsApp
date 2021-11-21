package com.example.newsapp.data.entities


/**
 * News response POJO
 */
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)