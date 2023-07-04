package com.i.kotlin

import android.widget.TextView

fun String.replaceA(): String {
    return this.replace("a", "1")
}

fun TextView.scaleSize(scale: Float){
    this.textSize = this.textSize*scale
}

fun fun1(param: () -> Unit) {
    param
}

fun fun2(param: (() -> Unit)?) {
    param
}

fun fun3(param: (Int, String) -> Unit) {
    println()
    param(5, "")
}

fun fun33(param: (Int) -> Unit) {
    println()
    param(2)
}

fun fun4(): (() -> Unit)? {

    return {}
}