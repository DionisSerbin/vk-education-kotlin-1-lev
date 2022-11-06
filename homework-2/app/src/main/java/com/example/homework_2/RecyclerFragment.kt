package com.example.homework_2

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.fragment_recycler.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class RecyclerFragment : Fragment(R.layout.fragment_recycler) {
    private val client = OkHttpClient()

    fun getGifLinks(): MutableList<String> {
        val imagesArr = mutableListOf<String>()
        val request = Request.Builder()
            .url("https://giphy.p.rapidapi.com/v1/gifs/trending?api_key=yY5LAD1stQaVe3gw1Ct1aQ9Zv2HroMVe&limit=2")
            .get()
            .addHeader("X-RapidAPI-Key", "6cd482a954msha4e953e0ff570a6p1aaebbjsn930fa125d64d")
            .addHeader("X-RapidAPI-Host", "giphy.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val body = response.body!!.string()
                    val jObject = JSONObject(body)
                    val arr = jObject.getJSONArray("data")

                    for (i in 0 until arr.length()) {
                        imagesArr.add(
                            arr.getJSONObject(i)
                                .getJSONObject("images")
                                .getJSONObject("original")
                                .getString("mp4")
                        )
                        println(imagesArr.last())
                    }
                }
            }
        })
        imagesArr.add("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif")
        imagesArr.add("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif")
        imagesArr.add("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif")
        imagesArr.add("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif")
        imagesArr.add("https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif")

        return imagesArr
    }

    private var adapter = DigitsRVAdapter(getGifLinks())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        digitsRV.adapter = adapter

        digitsRV.layoutManager = LinearLayoutManager(context)

        if (savedInstanceState != null) {
            adapter.updateDigits(getGifLinks())
        }

        view.imageButton.setOnClickListener {
//            digits.add(digits.size)
//            adapter.updateDigits(digits)
//            digitsRV.scrollToPosition(digits.size - 1)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}