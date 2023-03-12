package com.lh1169835.movietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1169835.movietracker.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting the info that was passed in
        var title = intent.getStringExtra("title")
        var genre = intent.getStringExtra("genre")
        var notes = intent.getStringExtra("note")
        var location = intent.getStringExtra("location")

        //population the text fields with the info passed in from the main activity
        binding.titleDetailsTextView.text = title
        binding.textGenre.text = genre
        binding.textLocation.text = location
        binding.noteText.text = notes

        //allowing the view movies button to take the user back to the home screen
        binding.viewMoviesBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}