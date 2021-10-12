package me.bakhtiyari.topfilms.ui.movies

import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        moviesViewModel.releaseYear.postValue(null)
    }

    override fun initObserves() {
        moviesViewModel.getMovies.observe(this, getMoviesObserver)
    }

    override fun initViews() {
        binding.gridMovieListView.layoutManager = GridLayoutManager(context, 2)
    }


    private fun loadMovies(movies: ArrayList<MovieModel>) {

        val adapter = UniversalDataBindingRecyclerAdapter(
            data = movies,
            layout = R.layout.item_grid_normall_movie_contents
        ) { view, model, _ ->
            view.setVariable(BR.actionsListener, this)
            view.setVariable(BR.content, model)
            view.executePendingBindings()
        }


        binding.gridMovieListView.adapter = adapter

    }

    override fun openMovie(movieId: Int) {

    }

    override fun onLoading(isLoading: Boolean) {

        moviesViewModel.isLoading.value = isLoading
    }

    override fun onGet(movies: ArrayList<MovieModel>) {

        onLoading(isLoading = false)
        loadMovies(movies)
    }

    override fun onError(msg: String) {
        msg.let {
            Timber.e("error: $it")
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

}