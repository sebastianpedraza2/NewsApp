package com.example.newsapp.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sebastián Pedraza on 21/09/2021.
 */
interface RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            /**
             * Creating an interceptor to print the BODY of the requests
             */
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            /**
             * Retrofit client
             */
            Retrofit.Builder().baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).client(client).build()
        }

        /**
         * The instance that im going to use everywhere
         */
        val api by lazy {
            retrofit.create(NewsApi::class.java)

        }
    }
}