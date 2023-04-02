package com.lh1169835.movietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.lh1169835.movietracker.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getting the info that was passed in
        var id = intent.getStringExtra("id")
        var title = intent.getStringExtra("title")
        var genre = intent.getStringExtra("genre")
        var notes = intent.getStringExtra("note")
        var location = intent.getStringExtra("location")
        //getting the Document Id
        val docId = title+"-"+id

        //population the text fields with the info passed in from the main activity
        binding.titleDetailsTextView.text = title
        binding.textGenre.text = genre
        binding.textLocation.text = location
        binding.noteText.text = notes

        //allowing the view movies button to take the user back to the home screen
        binding.viewMoviesBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.editBtn.setOnClickListener {
            var intent = Intent(this, EditMovieActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("docId", docId)
            intent.putExtra("title", title)
            intent.putExtra("genre", genre)
            intent.putExtra("note", notes)
            intent.putExtra("location", location)
            startActivity(intent)
        }

        //Deletes Document when pressed
        binding.deleteBtn.setOnClickListener {
            val db = FirebaseFirestore.getInstance().collection("movies").document(docId)
                .delete()
                .addOnSuccessListener {
                    Log.i("DeleteSuccess","$docId Deleted")
                    Toast.makeText(this,"Deleted Movie",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
                .addOnFailureListener {
                    Log.i("DeleteFail","Error Deleting")
                }

        }
    }
}