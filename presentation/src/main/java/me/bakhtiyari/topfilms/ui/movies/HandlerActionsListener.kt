package me.bakhtiyari.topfilms.ui.movies

import me.bakhtiyari.topfilms.domain.model.MovieModel

interface HandlerActionsListener {

    fun openMovie(movie: MovieModel)
}