package com.i.lib_network.core

import androidx.collection.ArrayMap
import com.i.lib_network.utils.LogUtil
import okhttp3.Interceptor
import okhttp3.Response

class CommonHeaderInterceptor(params: ArrayMap<String, String>?) : Interceptor {

    var mParams: ArrayMap<String, String>? = params

    fun setParams(params: ArrayMap<String, String>?) {
        mParams = params
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val tParams = mParams
        LogUtil.d(
            "CommonHeaderInterceptor",
            "intercept",
            "host: ${request.url.host}",
            "path: ${request.url.encodedPath}",
            "method: ${request.method}",
            "mParams: ${tParams?.toString()}"
        )
        return if (tParams == null || tParams.isEmpty) {
            chain.proceed(chain.request())
        } else {
            val builder = request.newBuilder()
            tParams.forEach { (k, v) ->
                builder.addHeader(k, v)
            }
            chain.proceed(builder.build())
        }
    }
}