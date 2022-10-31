package com.example.homework_1

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.fragment_recycler.view.*

private const val DIGIT_KEY: String = "DIGIT_KEY"
private const val PORTRAIT_COLUMNS = 3
private const val LANDSCAPE_COLUMNS = 4

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private var digits = arrayListOf(0)
    private var adapter = DigitsRVAdapter(digits)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        digitsRV.adapter = adapter

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            digitsRV.layoutManager = GridLayoutManager(context, PORTRAIT_COLUMNS)
        } else {
            digitsRV.layoutManager = GridLayoutManager(context, LANDSCAPE_COLUMNS)
        }

        if (savedInstanceState != null) {
            this.digits = savedInstanceState.getIntegerArrayList(DIGIT_KEY)!!
            adapter.updateDigits(digits)
        }

        view.imageButton.setOnClickListener {
            digits.add(digits.size)
            adapter.updateDigits(digits)
            digitsRV.scrollToPosition(digits.size - 1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(DIGIT_KEY, digits)
    }
}