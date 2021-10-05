package com.example.newsapp.util

/**
 * Created by Sebastián Pedraza on 01/09/2021.
 */
sealed class Resource<T>(

    val data: T? = null,
    val message: String? = null
) {

    /**
     * With a sealed class we define what classes can inherit from it
     */

    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class Failure<T>(message: String, data: T? = null) : Resource<T>(data, message)

}