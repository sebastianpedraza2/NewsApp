package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.ui.activities.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Sebastián Pedraza on 20/09/2021.
 */
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val viewModel by sharedViewModel<NewsViewModel>()
    lateinit var binding: FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        val article = args.article

        binding.webView.apply {
            /**
             * To ensure the webpage will open inside the app and not in the default device browser
             */
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
}