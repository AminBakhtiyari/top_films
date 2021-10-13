package me.bakhtiyari.topfilms.domain.repositoryImpl


import me.bakhtiyari.topfilms.data.network.api.ApiService
import me.bakhtiyari.topfilms.domain.convert.ListOfMovieToListOfMovieModelConvertor
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.domain.repository.ApiRepository
import javax.inject.Inject
import me.bakhtiyari.topfilms.domain.util.Result
import me.bakhtiyari.topfilms.domain.util.extension.handle

class ApiRepositoryImpl @Inject constructor(
    private val service: ApiService
): ApiRepository {

    override suspend fun getMovies(
        page: Int?,
        releaseYear: Int?
    ): Result<ArrayList<MovieModel>, String> {
        return try {
            val response = service.getMovies(page ?: 1, releaseYear)
            return Result.Success(ListOfMovieToListOfMovieModelConvertor().convert(response.results))
        } catch (e: Exception) {
            Result.Error(e.handle())
        }
    }
}