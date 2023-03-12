package com.lh1169835.movietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lh1169835.movietracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MovieViewAdapter.MovieItemListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMovieBtn.setOnClickListener {
            startActivity(Intent(this,AddMovieActivity::class.java))
        }

        //connects the viewModel, Recycler, and Adapter together
        val viewModel : MovieViewModel by viewModels()
        viewModel.getMovies().observe(this,{
            binding.recyclerView.adapter = MovieViewAdapter(this,it,this)
        })
    }

    override fun movieSelected(movie: Movie) {
        TODO("Not yet implemented")
    }
}