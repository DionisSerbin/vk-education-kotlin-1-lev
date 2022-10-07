package com.example.homework_1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_grid_layout.view.*

class DigitsRVAdapter(var digit: Int) : RecyclerView.Adapter<DigitsRVAdapter.ViewHolder>() {

    var digits = createDigitList(digit)

    fun createDigitList(dig: Int): MutableList<Int>  {
        var array = mutableListOf<Int>()
        for (i in 0..dig) {
            array.add(i)
        }
        return array
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_grid_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(digits[position])
    }

    override fun getItemCount() = digits.size

    fun updateDigits(newDigit: Int) {
        digits = createDigitList(newDigit)
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