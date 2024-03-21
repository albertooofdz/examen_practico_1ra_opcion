package com.example.examen_practico_1ra_opcion.data

import com.google.gson.annotations.SerializedName


data class SearchResponse (
        @SerializedName("Search") val search:List<MovieResponse>)

data class MovieResponse(
        @SerializedName("Title") var title: String,
        @SerializedName("Year") var year: String,
        @SerializedName("imdbID") var imdbID: String,
        @SerializedName("Type") var type: String,
        @SerializedName("Poster") var poster: String
)

data class DetailSearchResponse(
        @SerializedName("Title") var title: String,
        @SerializedName("Year") var year: String,
        @SerializedName("Runtime") var runtime: String,
        @SerializedName("Genre") var genre: String,
        @SerializedName("Director") var director: String,
        @SerializedName("Plot") var plot: String,
        @SerializedName("Country") var country: String,
        @SerializedName("Poster") var poster: String,

)
