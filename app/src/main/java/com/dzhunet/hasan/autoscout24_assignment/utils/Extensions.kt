package com.dzhunet.hasan.autoscout24_assignment.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.NumberFormat

inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes l: Int): View =
    LayoutInflater.from(context).inflate(l, this, false)


fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

inline val Int?.asCurrency: String
    get() = NumberFormat.getCurrencyInstance(Constants.DEFAULT_LOCALE).format(this ?: 0)