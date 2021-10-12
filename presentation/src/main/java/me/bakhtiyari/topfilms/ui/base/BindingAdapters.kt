package me.bakhtiyari.topfilms.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import me.bakhtiyari.topfilms.domain.util.extension.loadUrlImage

class BindingAdapters {


    companion object {

        private val TMDB_BASE_IMAGE_URL by lazy { "https://image.tmdb.org/t/p/w185" }

        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, imageUrl: String?) {

            imageView.loadUrlImage(imageUrl ?: "")

        }

        @JvmStatic
        @BindingAdapter("loadTMDBImage")
        fun loadTMDBImage(imageView: ImageView, imageUrl: String?) {



            imageView.loadUrlImage(TMDB_BASE_IMAGE_URL + imageUrl)

        }
    }

}