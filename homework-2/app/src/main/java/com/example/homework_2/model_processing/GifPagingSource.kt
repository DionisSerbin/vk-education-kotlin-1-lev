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
            val position = params.key ?: ONE_POSITION

            val response = accessor.getGiphy(
                apiKey = API_KEY,
                rating = RATING,
                limit = LIMIT
            )

            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = if (position == ONE_POSITION) null else position - ONE_POSITION,
                nextKey = position + ONE_POSITION
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GiphyData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_POSITION)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_POSITION)
        }
    }

    companion object {
        const val API_KEY = "yY5LAD1stQaVe3gw1Ct1aQ9Zv2HroMVe"
        const val RATING = "g"
        const val LIMIT = NETWORK_PAGE_SIZE
        const val ONE_POSITION = 1
    }

}
