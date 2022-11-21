package com.example.homework_2.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.homework_2.R
import com.example.homework_2.model.GiphyData

class GifViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    protected val image by lazy { view.findViewById<ImageView>(R.id.imageview) }
    val card = view.findViewById<CardView>(R.id.cardSample)
    protected val imageLoader by lazy { Glide.with(card) }

    fun bind(giphy: GiphyData) {
        val url = giphy.images.original.url
        imageLoader
            .load(url)
            .fitCenter()
            .downsample(DownsampleStrategy.CENTER_INSIDE)
            .dontTransform()
            .into(image)
    }
}