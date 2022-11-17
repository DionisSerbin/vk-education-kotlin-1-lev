package com.example.homework_2.model_processing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.homework_2.R

import com.example.homework_2.model.GiphyData
import com.velmurugan.paging3android.ui.MovieViewHolder

class GifPagerAdapter: PagingDataAdapter<GiphyData, MovieViewHolder>(GifComparator()) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val gifLink = getItem(position)
        if (gifLink != null) {
            holder.bind(gifLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return MovieViewHolder(view)
    }
}



