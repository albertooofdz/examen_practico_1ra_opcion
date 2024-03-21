package com.example.examen_practico_1ra_opcion.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_practico_1ra_opcion.R
import com.example.examen_practico_1ra_opcion.databinding.MovieListBinding
import com.squareup.picasso.Picasso

class MovieAdapter(
    var movieList: List<MovieResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit)
    : RecyclerView.Adapter<RecipeViewHolder>()

{
    fun updateList(recipesList: List<MovieResponse>) {

        this.movieList = recipesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(layoutInflater.inflate(R.layout.movie_list, parent, false))
    }

    override fun getItemCount() = movieList.size


    override fun onBindViewHolder(viewholder: RecipeViewHolder, position: Int) {

        viewholder.bind(movieList[position],onItemSelected)
    }

}


class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = MovieListBinding.bind(view)
    fun bind(movieResponse: MovieResponse, onItemSelected: (String) -> Unit) {
        binding.movieNameTV.text = movieResponse.title

        Picasso.get().load(movieResponse.poster).into(binding.moviePosterIV)


        binding.root.setOnClickListener{ onItemSelected(movieResponse.imdbID)}


        binding.movieYearTV.text= "Release Year: "+movieResponse.year

        binding.movieTypeTV.text= "Type: "+ movieResponse.type



    }



}