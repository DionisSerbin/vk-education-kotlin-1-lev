package com.example.homework_2.main

import androidx.lifecycle.ViewModel
import com.example.homework_2.async_request.IAccessor
import com.example.homework_2.gif_proccessing.GifProvider

class MainViewModel : ViewModel() {
    private val accessor = IAccessor.create()
    private val provider = GifProvider(accessor)

    suspend fun getGiphy() = provider.getGiphy(api_key = API_KEY, rating = "g", limit = 100)

    companion object {
        const val API_KEY = "yY5LAD1stQaVe3gw1Ct1aQ9Zv2HroMVe"
    }
}