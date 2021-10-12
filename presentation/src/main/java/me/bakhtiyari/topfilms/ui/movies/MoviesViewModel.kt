package me.bakhtiyari.topfilms.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import me.bakhtiyari.topfilms.domain.repository.ApiRepository
import javax.inject.Inject
import me.bakhtiyari.topfilms.domain.util.Result


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val page = 1
    val releaseYear = MutableLiveData<Int>()
    val isLoading = MutableLiveData<Boolean>()
    val msgError = MutableLiveData<String>()

    val getMovies = releaseYear.switchMap {

        liveData(Dispatchers.IO) {

            emit(Result.Loading())
            emit(apiRepository.getMovies(1, it))
        }
    }
}