package com.i.kotlin

import android.widget.TextView

fun String.replaceA(): String {
    return this.replace("a", "1")
}

fun TextView.scaleSize(scale: Float){
    this.textSize = this.textSize*scale
}