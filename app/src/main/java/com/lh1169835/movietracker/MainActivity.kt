package com.lh1169835.movietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.lh1169835.movietracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MovieViewAdapter.MovieItemListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //goes to the add movie activity
        binding.addMovieBtn.setOnClickListener {
            startActivity(Intent(this,AddMovieActivity::class.java))
        }

        //connects the viewModel, Recycler, and Adapter together
        val viewModel : MovieViewModel by viewModels()
        viewModel.getMovies().observe(this,{
            binding.recyclerView.adapter = MovieViewAdapter(this,it,this)
        })
    }

    //gets the selected movie
    override fun movieSelected(movie: Movie) {
        Log.i("Movie", "$movie");
        var intent = Intent(this, MovieDetailsActivity::class.java)

        //sends the data of the movie to the details activity
        intent.putExtra("id", movie.uid)
        intent.putExtra("title", movie.title)
        intent.putExtra("genre", movie.genre)
        intent.putExtra("note", movie.notes)
        intent.putExtra("location", movie.location)
        startActivity(intent)
    }
}