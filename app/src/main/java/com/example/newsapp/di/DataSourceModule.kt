package com.example.newsapp.di

import com.example.newsapp.data.remote.NewsDataSource
import com.example.newsapp.db.ArticleDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Configuracion para inyectar la capa de data source del proyecto
 */
val dataSourceModule = module {

    single { NewsDataSource(ArticleDatabase(androidContext())) }


}