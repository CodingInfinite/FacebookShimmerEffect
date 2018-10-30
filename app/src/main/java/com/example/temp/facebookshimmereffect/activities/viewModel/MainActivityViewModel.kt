package com.example.temp.facebookshimmereffect.activities.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.temp.facebookshimmereffect.backend.RetrofitClient
import com.example.temp.facebookshimmereffect.models.CustomMovieModel
import com.example.temp.facebookshimmereffect.response.MovieResponse
import com.example.temp.facebookshimmereffect.util.NonNullMediatorLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    companion object {
        private const val FIRST_PAGE = 1
    }

    private val _movies = NonNullMediatorLiveData<List<CustomMovieModel>>()
    private val _errors = NonNullMediatorLiveData<Throwable>()

    val movies: LiveData<List<CustomMovieModel>> = _movies
    val errors: LiveData<Throwable> = _errors

    private var moviesCall: Call<MovieResponse>? = null

    fun makeGetMovieCall() {
        moviesCall = RetrofitClient.getApiService()
            .getPopular(RetrofitClient.API_KEY, FIRST_PAGE)
        moviesCall?.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _errors.value = t
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val body: MovieResponse? = response.body()!!
                if (response.isSuccessful && body != null) {
                    val tempMovies = mutableListOf<CustomMovieModel>()
                    val size = body.movies.size - 1
                    for (i in 0..size step 2)
                        tempMovies.add(CustomMovieModel(body.movies[i], body.movies[i + 1]))
                    _movies.value = tempMovies
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        moviesCall?.cancel()
    }
}
