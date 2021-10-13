package me.bakhtiyari.topfilms.ui.movies

import me.bakhtiyari.topfilms.domain.model.MovieModel

interface StateManager {
    fun onLoading(isLoading: Boolean)
    fun onError(msg: String)
    fun showDialog(isShowing: Boolean)
}