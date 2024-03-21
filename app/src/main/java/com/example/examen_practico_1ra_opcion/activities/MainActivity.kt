package com.example.examen_practico_1ra_opcion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen_practico_1ra_opcion.R
import com.example.examen_practico_1ra_opcion.data.MovieAdapter
import com.example.examen_practico_1ra_opcion.data.MovieResponse
import com.example.examen_practico_1ra_opcion.data.MovieServiceApi
import com.example.examen_practico_1ra_opcion.data.SearchResponse
import com.example.examen_practico_1ra_opcion.databinding.ActivityMainBinding
import com.example.examen_practico_1ra_opcion.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()

    }

    private fun initList() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                searchByName(query.orEmpty())
                binding.searchView.clearFocus()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter= MovieAdapter() { id ->
            navigateToDetail(id) }

        binding.recycleMovie.setHasFixedSize(true)
        binding.recycleMovie.layoutManager = LinearLayoutManager(this)
        binding.recycleMovie.adapter = adapter

    }

    private fun searchByName(query: String) {

        binding.progressBar.isVisible = true

        val service: MovieServiceApi = RetrofitProvider.getRetrofit()


        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = service.searchByName(query)
            if (myResponse.isSuccessful){

                val response : SearchResponse? = myResponse.body()

                if (response!=null){
                    Log.i("hola","funciona")
                    Log.i("hola",response.toString())
                    runOnUiThread {
                        adapter.updateList(response.search)
                        binding.progressBar.isVisible=false
                    }
                }
            } else {
                Log.i("hola","no funciona")
            }

        }
    }
    private fun navigateToDetail(imdbID:String){
        val intent= Intent(this, MovieDetailActivity:: class.java)
        intent.putExtra("imdbID",imdbID)
        startActivity(intent)

    }
}