package com.example.temp.facebookshimmereffect.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.temp.facebookshimmereffect.R
import com.example.temp.facebookshimmereffect.models.CustomMovieModel
import com.example.temp.facebookshimmereffect.viewHolder.MovieViewHolder
import com.squareup.picasso.Picasso

class MovieAdapter constructor(
    private val context: Context,
    private val movies: List<CustomMovieModel>,
    private val picasso: Picasso = Picasso.with(context)
) :
    RecyclerView.Adapter<MovieViewHolder>() {

    companion object {
        const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185"
    }

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder =
        MovieViewHolder(layoutInflater.inflate(R.layout.single_movie_view, p0, false))

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val customMovieModel = movies[position]
        val leftUrl = IMAGE_BASE_URL.plus(customMovieModel.leftMovie.poster_path)
        val rightUrl = IMAGE_BASE_URL.plus(customMovieModel.rightMovie.poster_path)
        picasso.load(leftUrl).into(holder.leftMoviePosterImageView)
        picasso.load(rightUrl).into(holder.rightMoviePosterImageView)
        holder.leftMovieRatingTextView.text = customMovieModel.leftMovie.vote_average.toString()
        holder.leftMovieTitleTextView.setBackgroundColor(Color.WHITE)
        holder.leftMovieTitleTextView.text = customMovieModel.leftMovie.title
        holder.rightMovieRatingTextView.text = customMovieModel.rightMovie.vote_average.toString()
        holder.rightMovieTitleTextView.setBackgroundColor(Color.WHITE)
        holder.rightMovieTitleTextView.text = customMovieModel.rightMovie.title

    }
}