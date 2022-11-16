package com.example.homework_2.gif_proccessing

import com.example.homework_2.async_request.IAccessor

class GifProvider(val accessor: IAccessor) {

    suspend fun getGiphy(api_key: String, rating: String, limit: Int): List<Data> {

        return accessor
            .getGiphy(rating = rating, limit = limit, apiKey = api_key).data
    }
}