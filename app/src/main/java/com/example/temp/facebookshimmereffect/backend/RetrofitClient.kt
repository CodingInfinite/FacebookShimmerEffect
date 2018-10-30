package com.example.temp.facebookshimmereffect.backend

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder

object RetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val API_KEY = "e5c7041343c9969c537ddc8b720e80c7"

    private var gson = GsonBuilder()
        .create()

    private fun getRetrofitClient() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getApiService(): ServiceApi = getRetrofitClient().create(ServiceApi::class.java)
}