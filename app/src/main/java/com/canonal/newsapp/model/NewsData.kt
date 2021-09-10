package com.canonal.newsapp.model



import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class NewsData(
    val newsTitle: String,
    val newsText: String,
    val image: @RawValue Drawable?
) : Parcelable
