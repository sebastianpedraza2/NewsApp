package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.ui.activities.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Sebastián Pedraza on 20/09/2021.
 */
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private val viewModel by sharedViewModel<NewsViewModel>()
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentSavedNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        /**
         * Setting up the adapter for the rv
         */
        setupRecyclerView()

        /**
         * Set on click defined on the adapter, inside the adapter we pass it the article
         * To pass an article as a param we need to set it as a serializable or parcelable,
         * we can only pass primitive types
         */
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()

        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}