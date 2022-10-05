package com.example.homework_1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var digs = mutableListOf(0, 1)
    val adapter = DigitsRVAdapter(digs)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        digitsRV.adapter = adapter

        digitsRV.layoutManager = GridLayoutManager(this, 3)
    }


    fun addDigit(view: View) {

        digs.add(digs.last() + 1)
        adapter.updateDigits(digs)

        digitsRV.scrollToPosition(digs.size - 1)
    }
}