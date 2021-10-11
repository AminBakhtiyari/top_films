package me.bakhtiyari.topfilms.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieModel(
    var voteCount: Int? = null,
    var id: Int? = null,
    var video: Boolean? = null,
    var voteAverage: Float? = null,
    var title: String? = null,
    var popularity: Float? = null,
    var posterPath: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var adult: Boolean? = null,
    var overview: String? = null,
    var releaseDate: String? = null
) : Parcelable
