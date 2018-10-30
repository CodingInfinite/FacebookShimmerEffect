package com.example.temp.facebookshimmereffect.activities

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.temp.facebookshimmereffect.R
import com.example.temp.facebookshimmereffect.activities.viewModel.MainActivityViewModel
import com.example.temp.facebookshimmereffect.adapter.MovieAdapter
import com.example.temp.facebookshimmereffect.models.CustomMovieModel
import com.example.temp.facebookshimmereffect.util.nonNull
import com.example.temp.facebookshimmereffect.util.observe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val movies = mutableListOf<CustomMovieModel>()

    private val picasso = lazy {
        Picasso.with(this)
    }

    private val movieAdapter = lazy {
        MovieAdapter(this, movies, picasso.value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.makeGetMovieCall()
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.setHasFixedSize(true)
        moviesRecyclerView.adapter = movieAdapter.value
        startListenToMovies()
        startListenToErrors()
    }

    override fun onResume() {
        super.onResume()
        parentShimmerLayout.startShimmerAnimation()
    }

    private fun startListenToMovies() {
        viewModel.movies
            .nonNull()
            .observe(this) {
                parentShimmerLayout.visibility = View.GONE
                parentShimmerLayout.stopShimmerAnimation()
                movies.addAll(it)
                movieAdapter.value.notifyDataSetChanged()
            }
    }

    private fun startListenToErrors() {
        viewModel.errors
            .nonNull()
            .observe(this) {
                parentShimmerLayout.stopShimmerAnimation()
                Toast.makeText(this, "Error occurred in fetching movies!!", Toast.LENGTH_LONG).show()
            }
    }

    override fun onStop() {
        super.onStop()
        parentShimmerLayout.stopShimmerAnimation()
    }
}
