package com.example.homework_2.gif_proccessing
import com.google.gson.annotations.SerializedName


data class GiphyGenerated(
    @SerializedName("data")
    val `data`: List<Data>,
)

data class Data(
    @SerializedName("images")
    val images: Images,
)

data class Images(
    @SerializedName("original")
    val original: Original
)

data class Original(
    @SerializedName("url")
    val url: String
)