package com.lh1169835.movietracker

class Movie(
    var uid : String? = null,
    var title : String? = null,
    var notes : String? = null,
    var genre : String? = null,
    var location : String? = null
){
    override fun toString(): String {
        title?.let { return title!! }
        return "undefined"
    }
}