package com.example.homework_2.gif_proccessing

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.homework_2.R

class GifViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected val image by lazy { view.findViewById<ImageView>(R.id.image) }
    val card = view.findViewById<CardView>(R.id.cardSample)
    protected val imageLoader by lazy { Glide.with(card) }

    fun bind(giphy: Data) {
        println("!!!!!!!!!!!!!!!!!!!!")
        println(giphy.images.original.url)
        val url = giphy.images.original.url
        imageLoader
            .load(url)
            .fitCenter()
            .downsample(DownsampleStrategy.CENTER_INSIDE)
            .dontTransform()
            .into(image)
    }
}