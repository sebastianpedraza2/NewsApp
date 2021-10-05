package com.example.newsapp.ui.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.entities.NewsResponse
import com.example.newsapp.domain.news.NewsRepository
import com.example.newsapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import retrofit2.Response
import java.lang.Exception

class NewsViewModel(private val newsRepo: NewsRepository) : ViewModel() {

//    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    /**
     * Variable used for pagination
     */
    val page = 1

    fun getBreakingNews(countryCode: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(newsRepo.fetchBreakingNews(countryCode, page)))

        } catch (e: Exception) {
            emit(Resource.Failure(e.message.toString()))
        }

    }

    /**
     * This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
     */

//    init {
//        getBreakingNews("us")
//    }
//    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
//        breakingNews.postValue(Resource.Loading())
//        /**
//         * Handling the coroutine here
//         */
//        try{
//            breakingNews.postValue(Resource.Success(newsRepo.fetchBreakingNews(countryCode, page)))
//
//        }catch (e: Exception){
//            breakingNews.postValue(Resource.Failure(e.message.toString()))
//        }
//
//
//    }

    /**
     * Another way
     */

//    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
//        if (response.isSuccessful) {
//            response.body()?.let { newsResponse ->
//                return Resource.Success(newsResponse)
//            }
//        }
//        return Resource.Failure(response.message())
//
//
//    }


}