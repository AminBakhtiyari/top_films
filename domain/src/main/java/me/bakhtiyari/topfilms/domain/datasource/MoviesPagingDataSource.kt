package me.bakhtiyari.topfilms.domain.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.bakhtiyari.topfilms.data.network.api.ApiService
import me.bakhtiyari.topfilms.domain.convert.ListOfMovieToListOfMovieModelConvertor
import me.bakhtiyari.topfilms.domain.model.MovieModel

class MoviesPagingDataSource (
    private val service: ApiService,
    private val releaseYear: Int?
): PagingSource<Int, MovieModel>(){

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {

        val pageNumber = params.key ?: 1
        return try {
            val response = service.getMovies(pageNumber, releaseYear)
            LoadResult.Page(
                data = ListOfMovieToListOfMovieModelConvertor().convert(response.results),
                prevKey = null,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}