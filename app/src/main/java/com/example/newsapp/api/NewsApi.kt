package com.example.newsapp.api

import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sebastián Pedraza on 21/09/2021.
 */
interface NewsApi {

    /**
     * Getting the top headlines
     */

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        county: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        key: String = API_KEY,
    )

    /**
     * Getting all news
     */

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        querySearch: String,
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        key: String = API_KEY,
    )

}