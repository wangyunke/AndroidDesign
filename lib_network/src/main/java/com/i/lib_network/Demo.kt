package com.i.lib_network

import com.i.lib_network.bean.ApiResponse
import com.i.lib_network.bean.ErrorResult
import com.i.lib_network.bean.FailureResult
import com.i.lib_network.bean.SuccessResult
import com.i.lib_network.core.requestServer
import com.i.lib_network.utils.LogUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.http.*

internal object Demo {
    private val apiService = HttpRequestHelper.Builder().build().getApi(ApiService::class.java)

    @OptIn(DelicateCoroutinesApi::class)
    fun queryRecord() {
        GlobalScope.launch {
            val mutableMap = mutableMapOf<String, Any>()
            mutableMap["id"] = "123"
            val call = apiService.queryRecord(mutableMap)
            when (val awaitResult = call.requestServer()) {
                is SuccessResult -> {
                    LogUtil.d(
                        "OnSuccess",
                        "code: ${awaitResult.data.respCode}",
                        "message: ${awaitResult.data.respMsg}",
                        "traceId: ${awaitResult.data.traceId}",
                        "data: ${awaitResult.data.data}"
                    )
                }
                is FailureResult -> {
                    LogUtil.w(
                        "OnFailure",
                        "code: ${awaitResult.data.respCode}",
                        "message: ${awaitResult.data.respMsg}",
                        "traceId: ${awaitResult.data.traceId}",
                        "data: ${awaitResult.data.data}"
                    )
                }
                is ErrorResult -> {
                    LogUtil.w(
                        "OnError",
                        "code: ${awaitResult.errorObj.errorCode}",
                        "message: ${awaitResult.errorObj.errorMsg}",
                        "exception: ${awaitResult.errorObj.throwable?.localizedMessage}"
                    )
                }
            }
        }
    }

    interface ApiService {

        @FormUrlEncoded
        @POST("/../record/query")
        fun queryRecord(@FieldMap fields: MutableMap<String, Any>): Call<ApiResponse<Record>>
    }

    data class Record(
        val time: String,
        val mileage: String,
    )

}