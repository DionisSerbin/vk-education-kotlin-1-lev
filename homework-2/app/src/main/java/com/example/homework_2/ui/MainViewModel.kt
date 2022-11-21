package com.example.homework_2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework_2.model.GiphyData
import com.example.homework_2.model.GifPagerConfig

class MainViewModel constructor(private val gifPagerConfig: GifPagerConfig) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun getGifsList(): LiveData<PagingData<GiphyData>> {
        return gifPagerConfig.getGifsList().cachedIn(viewModelScope)
    }

}