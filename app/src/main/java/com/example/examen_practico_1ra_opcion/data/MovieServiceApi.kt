package com.example.examen_practico_1ra_opcion.data

import com.example.examen_practico_1ra_opcion.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServiceApi {
    @GET("?apikey=$API_KEY")
    suspend fun searchByName(@Query("s") movieName:String) : Response<SearchResponse>
    @GET("?apikey=$API_KEY")
    suspend fun searchById(@Query("i") movieId:String) : Response<DetailSearchResponse>

}