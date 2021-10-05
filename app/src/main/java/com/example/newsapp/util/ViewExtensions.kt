package com.example.newsapp.util

import android.opengl.Visibility
import android.view.View

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}