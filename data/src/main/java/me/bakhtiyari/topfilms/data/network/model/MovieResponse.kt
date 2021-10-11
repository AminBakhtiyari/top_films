package me.bakhtiyari.topfilms.data.network.model


data class MovieResponse (
    val page: Int = 0,
    val results: ArrayList<Movie>
)