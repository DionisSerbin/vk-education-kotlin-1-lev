package com.example.homework_1

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_grid_layout.view.*

class DigitsRVAdapter(var digits: MutableList<Int>, val listener: (value: Int, color: Int) -> Unit) :
    RecyclerView.Adapter<DigitsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.custom_grid_layout, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(digits[position])
    }

    override fun getItemCount() = digits.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateDigits(newDigits: MutableList<Int>) {
        digits = newDigits
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, val listener: (value: Int, color: Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val card = view.cardSample


        fun bind(digit: Int) {
            val color = if (digit % 2 == 1) {
                Color.BLUE
            } else {
                Color.RED
            }


            card.textView2.text = digit.toString()

            card.setOnClickListener {
                listener(digit, color)
            }

            card.setCardBackgroundColor(color)
        }
    }
}