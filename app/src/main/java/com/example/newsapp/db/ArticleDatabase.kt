package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.entities.Article


@Database(
    entities = [Article::class],
    version = 1,
)
@TypeConverters(
    Converters::class
)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object{
        /**
         * Thus, the writes to this field are immediately made visible to other threads.
         */
        @Volatile
        private var instance: ArticleDatabase? = null

        private val LOCK = Any()

        /**
         * Operator func Will run when trying to instantiate article database
         */
        // If it null then execute the bloc of code
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){ // sync threads
            // double checking: if another thread has assigned it
            instance ?: createDataBase(context).also{ instance = it}
        }

        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()

    }


}