package com.movie.util

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideUtil: GlideUtilInterface {

    override fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }
}