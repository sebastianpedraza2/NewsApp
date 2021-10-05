package com.example.newsapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.di.dataSourceModule
import com.example.newsapp.di.repositoryModule
import com.example.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private lateinit var navController: NavController

    private val viewModel by viewModel<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Starting DI KOIN
         */
        startKoin {
            androidLogger()
            androidContext(this@NewsActivity)
            modules(listOf(viewModelModule, repositoryModule, dataSourceModule))
        }

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 1. Setting up bottom nav
         */

          val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)


    }
}