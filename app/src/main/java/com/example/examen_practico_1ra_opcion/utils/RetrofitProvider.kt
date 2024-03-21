package com.example.examen_practico_1ra_opcion.utils

import com.example.examen_practico_1ra_opcion.data.MovieServiceApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        fun getRetrofit(): MovieServiceApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieServiceApi::class.java)
        }
    }
}