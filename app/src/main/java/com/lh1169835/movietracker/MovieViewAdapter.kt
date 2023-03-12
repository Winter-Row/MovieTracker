package com.lh1169835.movietracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewAdapter(val context : Context, val movies : List<Movie>, val itemListener : MovieItemListener) :
    RecyclerView.Adapter<MovieViewAdapter.MovieViewHolder>() {

    /**
     * This allows us to talk to the items in the layout file we created for the recyclerView
     * in this case that file is item_movie.xml
     */
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movieTitleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val notesTextView = itemView.findViewById<TextView>(R.id.notesTextView)
    }

    /**
     * This creates / connects the view to be put into the recycler view and inflates it
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //creating inflater
        val inflater = LayoutInflater.from(parent.context)
        //creating view
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        //returning view to be linked for access
        return MovieViewHolder(view)
    }

    /**
     * Returns the number of movies in the view
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * This populates the view with information from the given movie object
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder){
            movieTitleTextView.text = movie.title
            if(movie.notes.isNullOrEmpty()){
                notesTextView.text = "No notes for "+movie.title
            }else{
                notesTextView.text = movie.notes
            }
        }
    }

    interface MovieItemListener{
        fun movieSelected(movie : Movie)
    }
}