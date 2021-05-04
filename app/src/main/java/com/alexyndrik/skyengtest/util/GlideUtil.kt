package com.alexyndrik.skyengtest.util

import android.content.Context
import android.widget.ImageView
import com.alexyndrik.skyengtest.R
import com.bumptech.glide.Glide

object GlideUtil {

    fun loadImage(context: Context, urlWithoutHttp: String, imageView: ImageView) {
        val imageUrl = String.format(context.getString(R.string.image_url_template), urlWithoutHttp)
        Glide.with(context)
            .load(imageUrl.substring(0, imageUrl.indexOf("?")))
            .into(imageView)
    }

}