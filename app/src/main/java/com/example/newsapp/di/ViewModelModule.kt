package com.example.newsapp.di

import com.example.newsapp.ui.activities.NewsViewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Configuracion para inyectar los viewmodels del proyecto
 */
val viewModelModule = module {

    viewModel { NewsViewModel(get()) }

}