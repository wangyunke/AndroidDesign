package com.i.lib_network.bean

data class ErrorBean(
    val errorCode: Int,
    val errorMsg: String?,
    var throwable: Throwable?
)
