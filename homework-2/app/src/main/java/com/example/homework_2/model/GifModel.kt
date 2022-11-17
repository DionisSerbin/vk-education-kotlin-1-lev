package com.example.homework_2.model

import com.google.gson.annotations.SerializedName

const val NETWORK_PAGE_SIZE = 50

data class GiphyResponse(
    val page: Int,
    @SerializedName("data")
    val `data`: List<GiphyData>,
)

data class GiphyData(
    @SerializedName("images")
    val images: Images
)

data class Images(
    @SerializedName("original")
    val original: Original
)

data class Original(
    @SerializedName("url")
    val url: String
)