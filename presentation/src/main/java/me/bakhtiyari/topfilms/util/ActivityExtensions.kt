package me.bakhtiyari.topfilms.util

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle

fun Activity.refresh() {

    this.finish()
    this.overridePendingTransition(0, 0)
    this.startActivity(this.intent)
    this.overridePendingTransition(0, 0)
}


fun Activity.runActivity(
    c: Class<*>,
    clearTasks: Boolean = false,
    finish: Boolean = false,
    extras: Bundle.() -> Unit = {}
) {
    val intent = Intent(this, c)
    if (clearTasks) {
        intent.flags = FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = FLAG_ACTIVITY_CLEAR_TOP
    }
    val bundle = Bundle().apply(extras)
    if (!bundle.isEmpty) {

        intent.putExtras(bundle)
    }
    startActivity(intent)
    if (finish) {
        this.finish()
    }
}