package com.canonal.newsapp.model




import android.graphics.drawable.Drawable
import java.io.Serializable


class NewsData(
    val newsTitle: String,
    val newsText: String,
    val image:  Drawable?,
    val date: String
): Serializable
