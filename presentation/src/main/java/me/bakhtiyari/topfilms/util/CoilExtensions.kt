package me.bakhtiyari.topfilms.domain.util.extension

import android.widget.ImageView
import coil.load

fun ImageView.loadUrlImage(url: String) {

    this.load(url) {
        crossfade(true)
    }
}