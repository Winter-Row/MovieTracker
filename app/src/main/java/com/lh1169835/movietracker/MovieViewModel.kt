package com.lh1169835.movietracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MovieViewModel : ViewModel() {
    private var movies = MutableLiveData<List<Movie>>()

    init {
        val userId = Firebase.auth.currentUser.uid
        val db = FirebaseFirestore.getInstance().collection("movies")
            .whereEqualTo("uid",userId).orderBy("title")
            .addSnapshotListener{ documents, exception ->
                if(exception != null){
                    Log.w("DB_Response","Listen Failed${exception.code}")
                    return@addSnapshotListener
                }

                //if documents are not null
                documents?.let { documents
                    val movieList = ArrayList<Movie>()
                    for(document in documents){
                        Log.i("DB_Response","${document.data}")
                        val movie = document.toObject(Movie::class.java)
                        movieList.add(movie)
                    }
                    movies.value = movieList
                }
            }//end of Snapshot
    }//end of init

    fun getMovies() : LiveData<List<Movie>>{
        return movies
    }
}