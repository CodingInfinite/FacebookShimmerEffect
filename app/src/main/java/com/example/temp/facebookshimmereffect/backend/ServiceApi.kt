package com.example.temp.facebookshimmereffect.backend

import com.example.temp.facebookshimmereffect.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET(value = "popular")
    fun getPopular(
        @Query(
            value = "api_key"
        ) apiKey: String, @Query(
            value = "page"
        ) page: Int
    ): Call<MovieResponse>
}