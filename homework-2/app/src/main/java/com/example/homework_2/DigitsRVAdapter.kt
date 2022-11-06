package com.example.homework_2

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import kotlinx.android.synthetic.main.custom_grid_layout.view.*

class DigitsRVAdapter(var gifLinks: List<String>) :
    RecyclerView.Adapter<DigitsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.custom_grid_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifLinks[position])
    }

    override fun getItemCount() = gifLinks.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateDigits(newGifs: List<String>) {
        gifLinks = newGifs
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card = view.cardSample


        fun bind(image: String) {
            Glide.with(card)
                .asGif()
                .load(image)
//                .disallowHardwareConfig()
                .fitCenter()
                .downsample(DownsampleStrategy.CENTER_INSIDE)
                .dontTransform()
                .into(card.item_image)

            val color = Color.BLUE

            card.setCardBackgroundColor(color)
        }
    }
}