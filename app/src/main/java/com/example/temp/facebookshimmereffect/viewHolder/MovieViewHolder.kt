package com.example.temp.facebookshimmereffect.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.temp.facebookshimmereffect.R

class MovieViewHolder(itemView1: View) : RecyclerView.ViewHolder(itemView1) {

    val leftMoviePosterImageView: ImageView = itemView1.findViewById(R.id.leftMoviePosterImageView)
    val leftMovieRatingTextView: TextView = itemView1.findViewById(R.id.leftMovieRatingTextView)
    val leftMovieTitleTextView: TextView = itemView1.findViewById(R.id.leftMovieTitleTextView)
    val rightMoviePosterImageView: ImageView = itemView1.findViewById(R.id.rightMoviePosterImageView)
    val rightMovieRatingTextView: TextView = itemView1.findViewById(R.id.rightMovieRatingTextView)
    val rightMovieTitleTextView: TextView = itemView1.findViewById(R.id.rightMovieTitleTextView)
}