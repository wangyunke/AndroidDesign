package com.i.lib_network.bean

data class ApiResponse<out T>(
    val respCode: Int,

    val errorCode: String,

    val respMsg: String,

    val traceId: String?,

    val data: T?
)
