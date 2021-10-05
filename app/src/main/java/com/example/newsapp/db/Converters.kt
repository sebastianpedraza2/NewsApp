package com.example.newsapp.db

import androidx.room.TypeConverter
import com.example.newsapp.data.entities.Source

class Converters {

    @TypeConverter
    fun toString(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun fromString(name: String): Source {
        return Source(name, name)
    }
}