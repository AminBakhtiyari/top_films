package me.bakhtiyari.topfilms.util

import android.os.Handler
import android.os.Looper

fun ui(fn: () -> Unit) {
    val handler = Handler(Looper.getMainLooper())
    handler.post(fn)
}

fun delay(delay: Long, fn: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(fn, delay)
}