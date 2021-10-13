package me.bakhtiyari.topfilms.ui.movies

import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.bakhtiyari.topfilms.R
import me.bakhtiyari.topfilms.databinding.FragmentMoviesBinding
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.ui.base.BaseFragment
import me.bakhtiyari.topfilms.domain.util.Result
import me.bakhtiyari.topfilms.ui.base.UniversalDataBindingRecyclerAdapter
import timber.log.Timber

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(R.layout.fragment_movies), HandlerActionsListener, StateManager {

    private val moviesViewModel: MoviesViewModel by viewModels()

    private val getMoviesObserver = Observer<Result<ArrayList<MovieModel>, String>> {

        when (it) {
            is Result.Success -> {
                onGet(it.success() ?: arrayListOf())
            }
            is Result.Error -> {
                onError(it.error() ?: "")
            }
            is Result.Loading -> {
                onLoading(isLoading = true)
            }
        }
    }

    override fun initVariables() {

        binding.viewModel = moviesViewModel

    }

    override fun initObserves() {
        moviesViewModel.getMovies.observe(this, getMoviesObserver)
    }

    override fun initViews() {
        if (moviesViewModel.movies.value == null) {
            moviesViewModel.releaseYear.postValue(null)
        } else { loadMovies() }

    }


    private fun loadMovies() {

        val adapter = UniversalDataBindingRecyclerAdapter(
            data = moviesViewModel.movies.value ?: arrayListOf(),
            layout = R.layout.item_grid_normall_movie_contents
        ) { view, model, _ ->
            view.setVariable(BR.actionsListener, this)
            view.setVariable(BR.content, model)
            view.executePendingBindings()
        }

        binding.gridMovieListView.layoutManager = GridLayoutManager(context, 2)
        binding.gridMovieListView.adapter = adapter

    }

    override fun openMovie(movie: MovieModel) {
        val directions = MoviesFragmentDirections.actionMoviesFragmentToMovieFragment(movie)
        findNavController().navigate(directions)
    }

    override fun onLoading(isLoading: Boolean) {

        moviesViewModel.isLoading.value = isLoading
    }

    override fun onGet(movies: ArrayList<MovieModel>) {

        moviesViewModel.movies.value = movies
        onLoading(isLoading = false)
        loadMovies()
    }

    override fun onError(msg: String) {
        msg.let {
            Timber.e("error: $it")
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}