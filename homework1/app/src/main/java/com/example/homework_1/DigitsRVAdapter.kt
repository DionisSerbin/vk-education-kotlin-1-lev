package com.example.homework_1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_grid_layout.view.*

class DigitsRVAdapter(var digits: MutableList<Int>) : RecyclerView.Adapter<DigitsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_grid_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(digits[position])
    }

    override fun getItemCount() = digits.size

    fun updateDigits(newDigits: MutableList<Int>) {
        digits = newDigits
        notifyDataSetChanged()

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val card = view.cardSample

        fun bind(digit: Int) {
            card.textView2.text = digit.toString()
            if (digit % 2 == 1){
                card.setCardBackgroundColor(Color.BLUE)

            } else {
                card.setCardBackgroundColor(Color.RED)
            }
        }

    }
}