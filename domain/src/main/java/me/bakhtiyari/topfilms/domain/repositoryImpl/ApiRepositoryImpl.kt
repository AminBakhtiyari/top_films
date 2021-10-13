package me.bakhtiyari.topfilms.domain.repositoryImpl


import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import me.bakhtiyari.topfilms.data.network.api.ApiService
import me.bakhtiyari.topfilms.domain.datasource.MoviesPagingDataSource
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val service: ApiService
): ApiRepository {

    override suspend fun getMovies(releaseYear: Int?): LiveData<PagingData<MovieModel>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MoviesPagingDataSource(service, releaseYear)
        }
    ).liveData
}