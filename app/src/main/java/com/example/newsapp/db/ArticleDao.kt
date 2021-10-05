package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.data.entities.Article

/**
 * Created by Sebastián Pedraza on 22/09/2021.
 * Interface that will handle all db operations
 */

@Dao
interface ArticleDao {

    /**
     *  Stands for update or insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: Article): Long  // Returns the id that was inserted

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    fun deleteArticle(article: Article)
}