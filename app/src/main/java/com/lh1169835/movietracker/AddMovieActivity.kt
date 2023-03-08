package com.lh1169835.movietracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lh1169835.movietracker.databinding.ActivityAddMovieBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AddMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.MovieAddBtn.setOnClickListener{
            var title = binding.textMovieTitle.text.toString()
            var genre = binding.genreSpinner.selectedItem.toString()
            var location = binding.locationSpinner.selectedItem.toString()
            var notes = binding.textAddNotes.text.toString()

            //creating movie if title, genre, and location are filled in
            if (title.isNotEmpty() && genre.isNotEmpty() && location.isNotEmpty()){
//                var uID = auth.currentUser.uid can use after sign in is implemented
                var uID = auth.uid
                //creating Movie to be added to firestore
                var movie = Movie(uID,title, notes, genre, location)

                val db = FirebaseFirestore.getInstance().collection("movies")

                var docId = title + "-" + uID
                db.document(docId).set(movie).addOnSuccessListener {
                    Toast.makeText(this,"Movie Added",Toast.LENGTH_LONG).show()
                    binding.textAddNotes.text.clear()
                    binding.textMovieTitle.text.clear()
                    binding.genreSpinner.setSelection(0)
                    binding.locationSpinner.setSelection(0)

                    //reads from the db and log the movies in the db
                    db.get().addOnSuccessListener { collection ->
                        for (document in collection){
                            Log.i("Firestore","${document.id}=>${document.data}")
                        }
                    }
                }.addOnFailureListener {
                        exception -> Log.w("DB_Issue",exception.localizedMessage)
                }
            }else{
                Toast.makeText(this,"Title, Genre, And Location are required",Toast.LENGTH_LONG).show()
            }
        }
    }
}