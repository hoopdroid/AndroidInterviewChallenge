package com.excelsior.codechallenge.infrastructure.utils

import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.showOrInvisible(show: Boolean) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.INVISIBLE
    }
}

fun View.showOrGone(show: Boolean?) {
    visibility = when (show) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}
