package me.bakhtiyari.topfilms.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import me.bakhtiyari.topfilms.domain.model.MovieModel


interface ApiRepository {
    suspend fun getMovies(releaseYear: Int?): LiveData<PagingData<MovieModel>>
}