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

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {
    var digs = mutableListOf(0, 1)
    var adapter = DigitsRVAdapter(digs)
    val DIGIT_KEY: String = "DIGIT_KEY"

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//            println("???????????")
//        if (savedInstanceState != null) {
//            this.digs = savedInstanceState.getIntegerArrayList(DIGIT_KEY)!!.toMutableList()
//            adapter.updateDigits(digs)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_recycler, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        digitsRV.adapter = adapter
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            digitsRV.layoutManager = GridLayoutManager(context, 3)

        } else {
            digitsRV.layoutManager = GridLayoutManager(context, 4)
        }
        if (savedInstanceState != null) {
            this.digs = savedInstanceState.getIntegerArrayList(DIGIT_KEY)!!.toMutableList()
            adapter.updateDigits(digs)
        }
        view.imageButton.setOnClickListener{
            digs.add(digs.last() + 1)
            adapter.updateDigits(digs)
            digitsRV.scrollToPosition(digs.size - 1)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(DIGIT_KEY, ArrayList(digs))
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        if (savedInstanceState != null) {
//            println("!!!!!!!!!!!")
//            digs = savedInstanceState.getIntegerArrayList(DIGIT_KEY)!!.toMutableList()
//        }
//    }
}