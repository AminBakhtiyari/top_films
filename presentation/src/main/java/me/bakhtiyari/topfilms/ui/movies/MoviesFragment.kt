package me.bakhtiyari.topfilms.ui.movies

import android.app.Dialog
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.bakhtiyari.topfilms.R
import me.bakhtiyari.topfilms.databinding.DialogSelectYearBinding
import me.bakhtiyari.topfilms.databinding.FragmentMoviesBinding
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.ui.base.BaseFragment
import me.bakhtiyari.topfilms.ui.base.UniversalDataBindingRecyclerAdapter
import timber.log.Timber

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(R.layout.fragment_movies), HandlerActionsListener, StateManager {

    private val moviesViewModel: MoviesViewModel by viewModels()

    private val movieListAdapter by lazy {
        MovieListAdapter(this)
    }

    private lateinit var selectYearDialog: Dialog

    override fun initVariables() {

        binding.viewModel = moviesViewModel
        binding.actionsListener = this

    }

    override fun initObserves() {
        moviesViewModel.movies.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }
    }

    override fun initViews() {
        binding.gridMovieListView.layoutManager = GridLayoutManager(context, 2)
        binding.gridMovieListView.adapter = movieListAdapter
        initAdapter()
    }


    override fun openMovie(movie: MovieModel) {
        val directions = MoviesFragmentDirections.actionMoviesFragmentToMovieFragment(movie)
        findNavController().navigate(directions)
    }

    override fun openSelectYear() {
        selectYearDialog = getSelectYearDialog()
        showDialog(true)
    }

    override fun selectedYear(year: String) {
        showDialog(false)
        moviesViewModel.releaseYear.value = when(year) {
            "All" -> null
            else -> year.toInt()
        }
        moviesViewModel.refresh()


    }

    override fun onLoading(isLoading: Boolean) {

        moviesViewModel.isLoading.value = isLoading
    }

    override fun onError(msg: String) {
        msg.let {
            Timber.e("error: $it")
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun showDialog(isShowing: Boolean) {
        when(isShowing) {
            true -> selectYearDialog.show()
            else -> selectYearDialog.dismiss()
        }
    }

    private fun initAdapter() {
        movieListAdapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty = loadState.refresh is LoadState.NotLoading && movieListAdapter.itemCount == 0
            moviesViewModel.isVisible.value = isListEmpty

            // Only show the list if refresh succeeds.
            moviesViewModel.isVisible.value = loadState.source.refresh is LoadState.NotLoading

            // Show loading spinner during initial load or refresh.
            onLoading(loadState.source.refresh is LoadState.Loading)

            // If we have an error, show a toast
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                onError(it.error.message.toString())
            }
        }
    }

    private fun getSelectYearDialog(): Dialog {

        val dialog = Dialog(binding.body.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = DialogSelectYearBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(dialogBinding.root)


        val years = arrayListOf<String>()
        years.add("All")
        years.addAll((1990..2021).toList().reversed().map { n -> n.toString() })

        val adapter = UniversalDataBindingRecyclerAdapter(
            data = years,
            layout = R.layout.item_year_contents

        ) { view, model, _ ->

            view.setVariable(BR.actionsListener, this)
            view.setVariable(BR.content, model)
            view.executePendingBindings()
        }

        dialogBinding.gridListYears.layoutManager = LinearLayoutManager(binding.body.context)
        dialogBinding.gridListYears.adapter = adapter


        return dialog
    }
}