package com.lh1169835.movietracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1169835.movietracker.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title = intent.getStringExtra("title")
        var genre = intent.getStringExtra("genre")
        var notes = intent.getStringExtra("note")
        var location = intent.getStringExtra("location")

        binding.titleDetailsTextView.text = title
        binding.textGenre.text = genre
        binding.textLocation.text = location
        binding.noteText.text = notes
    }
}