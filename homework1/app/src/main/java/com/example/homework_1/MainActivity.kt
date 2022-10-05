package com.example.homework_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun countMe(view: View){
        val countStr = textView.text.toString()

        var count: Int = Integer.parseInt(countStr)
        count++

        textView.text = count.toString()

    }
}