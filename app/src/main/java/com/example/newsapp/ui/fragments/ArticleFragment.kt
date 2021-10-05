package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.ui.activities.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Sebastián Pedraza on 20/09/2021.
 */
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val viewModel by sharedViewModel<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}