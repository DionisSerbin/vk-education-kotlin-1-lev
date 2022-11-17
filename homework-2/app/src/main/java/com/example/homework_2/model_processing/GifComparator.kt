package com.example.homework_2.model_processing

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_2.model.GiphyData

class GifComparator: DiffUtil.ItemCallback<GiphyData>()  {
    override fun areItemsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
        return oldItem == newItem
    }
}