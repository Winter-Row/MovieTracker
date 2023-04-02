package com.lh1169835.movietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isEmpty
import com.google.firebase.firestore.FirebaseFirestore
import com.lh1169835.movietracker.databinding.ActivityAddMovieBinding
import com.lh1169835.movietracker.databinding.ActivityEditMovieBinding

class EditMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditMovieBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //getting the information passed from the last activity
        var id = intent.getStringExtra("id")
        var title = intent.getStringExtra("title")
        var genre = intent.getStringExtra("genre")
        var notes = intent.getStringExtra("note")
        var location = intent.getStringExtra("location")
        var docId = intent.getStringExtra("docId")

        //populating the input fields
        binding.textMovieTitle.setText(title)
        binding.textAddNotes.setText(notes)
        //Save button updates the movie by deleting document and creating new one with
        //info from the old document and with changes the user made
        binding.MovieSaveBtn.setOnClickListener {
            val db = FirebaseFirestore.getInstance().collection("movies")

            //deleting Document so the docId do not get mixed up
            db.document(docId!!).delete()

            //setting the title and notes variables from the EditText boxes
            title = binding.textMovieTitle.text.toString()
            notes = binding.textAddNotes.text.toString()

            //Only change the genre and location vars if the Spinner values are not default
            if(binding.genreSpinner.selectedItemId > 0){
                genre = binding.genreSpinner.selectedItem.toString()
            }
            if (binding.locationSpinner.selectedItemId > 0){
                location = binding.locationSpinner.selectedItem.toString()
            }

            //recreating the document with users changes
            docId = title+"-"+id
            var movie = Movie(id,title, notes, genre, location)
            db.document(docId!!).set(movie).addOnSuccessListener {
                Toast.makeText(this,"Movie Updated", Toast.LENGTH_LONG).show()
            }
            //going back to the main activity
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}