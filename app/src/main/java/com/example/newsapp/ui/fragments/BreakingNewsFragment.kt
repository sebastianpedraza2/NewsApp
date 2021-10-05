package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.ui.activities.NewsViewModel
import com.example.newsapp.util.Resource
import com.example.newsapp.util.hide
import com.example.newsapp.util.show
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Sebastián Pedraza on 20/09/2021.
 */
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {
    private val viewModel by sharedViewModel<NewsViewModel>()

    lateinit var binding: FragmentBreakingNewsBinding
    lateinit var newsAdapter: NewsAdapter

    private val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Setting up view binding
         */
        binding = FragmentBreakingNewsBinding.bind(view)
        /**
         * Setting up the adapter for the rv
         */
        setupRecyclerView()

        viewModel.getBreakingNews("us").observe(viewLifecycleOwner, Observer { result ->
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

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()

        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}