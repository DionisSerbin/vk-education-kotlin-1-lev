package com.example.homework_2.model



import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.homework_2.model_processing.GifPagingSource
import com.example.homework_2.network.IAccessor


class GifPagerConfig constructor(private val accessor: IAccessor) {

    fun getGifsList(): LiveData<PagingData<GiphyData>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                GifPagingSource(accessor)
            }
            , initialKey = 1
        ).liveData
    }

}