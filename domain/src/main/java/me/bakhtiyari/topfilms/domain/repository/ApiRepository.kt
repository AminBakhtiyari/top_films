package me.bakhtiyari.topfilms.domain.repository

import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.domain.util.Result


interface ApiRepository {
    suspend fun getMovies(page: Int?, releaseYear: Int?): Result<ArrayList<MovieModel>, String>
}