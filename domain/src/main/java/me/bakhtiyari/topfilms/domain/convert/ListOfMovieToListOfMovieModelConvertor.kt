package me.bakhtiyari.topfilms.domain.convert

import me.bakhtiyari.topfilms.data.network.model.Movie
import me.bakhtiyari.topfilms.domain.model.MovieModel
import me.bakhtiyari.topfilms.domain.util.Converter

class ListOfMovieToListOfMovieModelConvertor: Converter<ArrayList<MovieModel>, ArrayList<Movie>> {
    override fun convert(objects: ArrayList<Movie>): ArrayList<MovieModel> {
        var result: ArrayList<MovieModel> = arrayListOf()
        objects.forEach { movie ->
            result.add(
                MovieModel(
                movie.voteCount,
                movie.id,
                movie.video,
                movie.voteAverage,
                movie.title,
                movie.popularity,
                movie.posterPath,
                movie.originalLanguage,
                movie.originalTitle,
                movie.adult,
                movie.overview,
                movie.releaseDate
            )
            )
        }

        return result;
    }

}