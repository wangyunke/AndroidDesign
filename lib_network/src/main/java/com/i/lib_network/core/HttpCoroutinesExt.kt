package com.i.lib_network.core

import androidx.annotation.IntRange
import com.i.lib_network.bean.ApiError
import com.i.lib_network.bean.ApiResponse
import com.i.lib_network.bean.ApiResult
import com.i.lib_network.bean.ErrorBean
import com.i.lib_network.bean.ErrorResult
import com.i.lib_network.bean.FailureResult
import com.i.lib_network.bean.SuccessResult
import com.i.lib_network.utils.Config
import com.i.lib_network.utils.LogUtil
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 *
 * val call = HttpNetHelper.service.queryMaintenanceRecord(paramsMap)
 * val result = call.awaitResult()
 * when(result) {
 *      is SuccessResult -> {}
 *      is FailureResult -> {}
 *      is ErrorResult -> {}
 * }
 *
 * How to cancel request?
 * - call.cancel()
 * - coroutine.cancel()
 */
suspend fun <T : Any> Call<T>.requestServer(): ApiResult<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                if (continuation.isCancelled || isCanceled) return
                val result = Result.success(
                    try {
                        if (response?.isSuccessful == true) {
                            val body = response.body()
                            LogUtil.d("requestServer", "onResponse body=${body.toString()}")
                            if (body != null) {
                                if (body is ApiResponse<*>) {
                                    if (body.respCode == Config.RESPONSE_CODE_SUCCESS) {
                                        SuccessResult(body)
                                    } else {
                                        FailureResult(body)
                                    }
                                } else {
                                    SuccessResult(body)
                                }
                            } else {
                                ErrorResult(ApiError.unknownError)
                            }
                        } else {
                            ErrorResult(
                                ErrorBean(
                                    response?.code() ?: ApiError.emptyDataErrorCode,
                                    response?.message() ?: "",
                                    IllegalStateException("response is null or http response status code is not 200, code: ${response?.code()}")
                                )
                            )
                        }
                    } catch (e: Exception) {
                        ErrorResult(
                            ErrorBean(
                                ApiError.parseDataErrorCode,
                                "response data parse error",
                                e
                            )
                        )
                    }
                )
                if (continuation.isCancelled || isCanceled) return
                continuation.resumeWith(result)
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                if (continuation.isCancelled || isCanceled) return
                continuation.resumeWith(
                    Result.success(
                        ErrorResult(
                            handleException(
                                t ?: NullPointerException()
                            )
                        )
                    )
                )
            }

        })

        registerOnCoroutineCancel(continuation)
    }
}

suspend fun <T : Any> Call<T>.awaitRetryResult(
    @IntRange(from = 0) retryNum: Int = 0,
    @IntRange(from = 0) delayTime: Long = 0
): ApiResult<T> {
    val s: ApiResult<T> = suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                if (continuation.isCancelled || isCanceled) return
                val result = Result.success(
                    try {
                        if (response?.isSuccessful == true) {
                            val data = response.body()
                            if (data != null) {
                                if (data is ApiResponse<*>) {
                                    if (data.respCode == Config.RESPONSE_CODE_SUCCESS) {
                                        SuccessResult(data)
                                    } else {
                                        FailureResult(data)
                                    }
                                } else {
                                    SuccessResult(data)
                                }
                            } else {
                                ErrorResult(ApiError.unknownError)
                            }
                        } else {
                            ErrorResult(
                                ErrorBean(
                                    response?.code() ?: ApiError.emptyDataErrorCode,
                                    response?.message() ?: "",
                                    IllegalStateException("response is null or http response status code is not 200, code: ${response?.code()}")
                                )
                            )
                        }
                    } catch (e: Exception) {
                        ErrorResult(
                            ErrorBean(
                                ApiError.parseDataErrorCode,
                                "response data parse error",
                                e
                            )
                        )
                    }
                )
                if (continuation.isCancelled || isCanceled) return
                continuation.resumeWith(result)
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                if (continuation.isCancelled || isCanceled) return
                continuation.resumeWith(
                    Result.success(
                        ErrorResult(handleException(t ?: NullPointerException()))
                    )
                )
            }
        })

        registerOnCoroutineCancel(continuation)
    }
// 请求失败重连
    if (retryNum > 0 && s is ErrorResult) {
        if (s.errorObj == ApiError.netError) {
//        if (s.errorObj == ApiError.netError || s.errorObj == ApiError.serverError) {
            if (delayTime > 0) {
                delay(delayTime)
            }
            return this.clone().awaitRetryResult(retryNum - 1, delayTime)
        }
    }
    return s
}

/**
 * 注册监听，当协程的生命周期走到cancel时，同时将网络请求执行取消操作
 */
private fun Call<*>.registerOnCoroutineCancel(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        try {
            cancel()
        } catch (ex: Throwable) {
            //Ignore cancel exception
        }
    }
}

private fun handleException(e: Throwable): ErrorBean {
    e.printStackTrace()
    return when (e) {
        is SocketTimeoutException, is ConnectException, is HttpException, is UnknownHostException -> {
            // 网络错误
            ApiError.netError
        }

        is JSONException, is ParseException, is IllegalArgumentException -> {
            // 解析错误
            ApiError.serverError
        }

        else -> {
            // 未知错误
            ApiError.unknownError.throwable = e
            ApiError.unknownError
        }
    }
}