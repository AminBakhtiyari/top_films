package me.bakhtiyari.topfilms.ui.movies

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.domain.repository.ApiRepository
import me.bakhtiyari.topfilms.ui.base.RefreshableLiveData
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val releaseYear = MutableLiveData<Int>()

    val isLoading = MutableLiveData<Boolean>()
    val isVisible = MutableLiveData<Boolean>()
    val msgError = MutableLiveData<String>()

    val movies = RefreshableLiveData {
        runBlocking {
            getMovies()
        }
    }

    fun refresh() {
        movies.refresh()
    }

    private suspend fun getMovies(): LiveData<PagingData<MovieModel>> {
        return apiRepository.getMovies(releaseYear.value).cachedIn(viewModelScope)
    }
}