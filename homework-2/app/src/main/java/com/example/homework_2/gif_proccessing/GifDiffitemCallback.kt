package com.example.homework_2.gif_proccessing

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class GifDiffitemCallback: DiffUtil.ItemCallback<Data>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}