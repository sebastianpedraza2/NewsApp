package com.example.newsapp.di

import com.example.newsapp.domain.news.NewsRepoImpl
import com.example.newsapp.domain.news.NewsRepository
import org.koin.dsl.module

/**
 * Configuracion para inyectar la capa de repositorio del proyecto
 */
val repositoryModule = module {

    single<NewsRepository> { NewsRepoImpl(get()) }

}