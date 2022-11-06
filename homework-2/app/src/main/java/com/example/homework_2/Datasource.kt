package com.example.homework_2

import com.example.homework_2.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Datasource() {

    private val client = OkHttpClient()

    val imagesArr = mutableListOf<String>()

    fun getGifTrendingLinks(): MutableList<String> {
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
                                .getString("url")
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

    fun getGifRandomLink(): String{
        var link = ""
        val request = Request.Builder()
            .url("https://api.giphy.com/v1/gifs/random?api_key=yY5LAD1stQaVe3gw1Ct1aQ9Zv2HroMVe&tag=&rating=g")
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
                    link = jObject
                        .getJSONObject("data")
                        .getJSONObject("images")
                        .getJSONObject("original")
                        .getString("url")
                }
            }
        })
        return link
    }
}