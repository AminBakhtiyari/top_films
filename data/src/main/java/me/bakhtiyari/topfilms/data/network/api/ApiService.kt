package me.bakhtiyari.topfilms.data.network.api

import me.bakhtiyari.topfilms.data.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int = 1): MovieResponse
}