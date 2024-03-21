package com.example.examen_practico_1ra_opcion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.examen_practico_1ra_opcion.R
import com.example.examen_practico_1ra_opcion.data.DetailSearchResponse
import com.example.examen_practico_1ra_opcion.data.MovieResponse
import com.example.examen_practico_1ra_opcion.data.MovieServiceApi
import com.example.examen_practico_1ra_opcion.data.SearchResponse
import com.example.examen_practico_1ra_opcion.databinding.ActivityMovieDetailBinding
import com.example.examen_practico_1ra_opcion.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    lateinit var movie : DetailSearchResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imdbID =intent.getStringExtra ("imdbID")






        getRecipeInfo(imdbID!!)
    }

    private fun getRecipeInfo(imdbID: String) {

        val service: MovieServiceApi = RetrofitProvider.getRetrofit()


        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = service.searchById(imdbID)
            if (myResponse.isSuccessful){
                val response : DetailSearchResponse? = myResponse.body()

                if (response!=null){
                    Log.i("hola",imdbID)
                    Log.i("hola","funciona")
                    Log.i("hola",response.toString())
                    runOnUiThread {
                        movie=response
                        loadData()

                    }
                }
            }
        }
    }
  private fun loadData(){

      binding.titleTV.text=movie.title
      binding.yearTV.text=movie.year
      Picasso.get().load(movie.poster).into(binding.posterIV)
      binding.plotTV.text=movie.plot
      binding.runtimeTV.text=movie.runtime
      binding.directorTV.text=movie.director
      binding.genreTV.text=movie.genre
      binding.countryTV.text=movie.country








    }
}
