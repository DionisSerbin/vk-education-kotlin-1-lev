package com.example.homework_2.gif_proccessing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.homework_2.R

class GifAdapter : ListAdapter<Data, GifViewHolder>(GifDiffitemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }
}