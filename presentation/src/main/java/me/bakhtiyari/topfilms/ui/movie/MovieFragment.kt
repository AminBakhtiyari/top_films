package me.bakhtiyari.topfilms.ui.movie

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.bakhtiyari.topfilms.R
import me.bakhtiyari.topfilms.databinding.FragmentMovieBinding
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.ui.base.BaseFragment

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private val movieViewModel: MovieViewModel by viewModels()
    private val args: MovieFragmentArgs by navArgs()
    private val movie: MovieModel by lazy { args.movie }

    override fun initVariables() {
        binding.viewModel = movieViewModel
        binding.content = movie
    }

    override fun initObserves() {
    }

    override fun initViews() {
    }
}