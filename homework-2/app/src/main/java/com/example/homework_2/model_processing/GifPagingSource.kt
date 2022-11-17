package com.example.homework_2.model_processing

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_2.model.GiphyData
import com.example.homework_2.model.NETWORK_PAGE_SIZE
import com.example.homework_2.network.IAccessor
import java.lang.Exception

class GifPagingSource(private val accessor: IAccessor): PagingSource<Int, GiphyData>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyData> {

        return try {
            val position = params.key ?: 1

            val response = accessor.getGiphy(
                apiKey = API_KEY,
                rating = RATING,
                limit = LIMIT
            )

            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GiphyData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val API_KEY = "yY5LAD1stQaVe3gw1Ct1aQ9Zv2HroMVe"
        const val RATING = "g"
        const val LIMIT = NETWORK_PAGE_SIZE
    }

}
