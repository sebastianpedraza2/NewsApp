package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.ui.activities.NewsViewModel
import com.example.newsapp.util.Constants.Companion.SEARCH_NEWS_DEBOUNCE
import com.example.newsapp.util.Resource
import com.example.newsapp.util.hide
import com.example.newsapp.util.show
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Sebastián Pedraza on 20/09/2021.
 */
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {
    private val viewModel by sharedViewModel<NewsViewModel>()

    lateinit var binding : FragmentSearchNewsBinding
    lateinit var newsAdapter: NewsAdapter

    private val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchNewsBinding.bind(view)
        setupRecyclerView()
        onSearchListener()
    }

    /**
     * Search functionality with 400 milli of debounce
     */

    var job: Job? = null

    private fun onSearchListener() {
        binding.etSearch.addTextChangedListener { editable ->
            /**
             * Dispose the current job if the user types something else during the delay time
             */
        job?.cancel()
            /**
             * create a new one in the Dispatchers.main
             */
            job = MainScope().launch {
                delay(SEARCH_NEWS_DEBOUNCE)

                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString()).observe(viewLifecycleOwner, Observer { result ->
                            when(result){
                                is Resource.Loading ->{
                                    binding.paginationProgressBar.show()

                                }
                                is Resource.Success ->{
                                    binding.paginationProgressBar.hide()
                                    result.data?.let {
                                        newsAdapter.differ.submitList(it.articles)
                                    }
                                }
                                is Resource.Failure ->{
                                    binding.paginationProgressBar.hide()
                                    result.message?.let {
                                        Log.e(TAG, "An error ocurred: $it" )
                                    }

                                }
                            }
                        })
                    }
                }

            }

        }
    }


    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()

        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}