package com.example.homework_2.network

import com.example.homework_2.model.GiphyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IAccessor {

    @GET("gifs/trending")
    suspend fun getGiphy(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String,
    ): Response<GiphyResponse>

    companion object {
        fun create() : IAccessor {

            val client = OkHttpClient.Builder().build()

            val restrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://api.giphy.com/v1/")
            }.build()

            return restrofit.create(IAccessor::class.java)
        }

    }
}