package com.example.homework_2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework_2.model.GifPagerConfig
import com.example.homework_2.network.IAccessor

class MyViewModelFactory: ViewModelProvider.Factory {

    private val retrofitService = IAccessor.create()
    private val gifPagerConfig = GifPagerConfig(retrofitService)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.gifPagerConfig) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}