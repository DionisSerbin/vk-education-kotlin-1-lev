package com.example.homework_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.homework_2.ui.MainFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity, MainFragment.newInstance())
                .commitNow()
        }
    }

}