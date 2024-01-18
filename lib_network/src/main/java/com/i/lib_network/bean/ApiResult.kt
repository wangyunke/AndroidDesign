package com.i.lib_network.bean

sealed class ApiResult<out T>

data class SuccessResult<out T>(val data: T) : ApiResult<T>()

data class FailureResult<out T>(val data: T) : ApiResult<T>()

data class ErrorResult(val errorObj: ErrorBean) : ApiResult<Nothing>()