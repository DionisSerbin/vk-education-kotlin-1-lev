package com.example.homework_2.model_processing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.homework_2.R

import com.example.homework_2.model.GiphyData
import com.example.homework_2.ui.GifViewHolder

class GifPagerAdapter: PagingDataAdapter<GiphyData, GifViewHolder>(GifComparator()) {

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifLink = getItem(position)
        if (gifLink != null) {
            holder.bind(gifLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return GifViewHolder(view)
    }
}



