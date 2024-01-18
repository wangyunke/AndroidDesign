package com.i.lib_network

import androidx.collection.ArrayMap
import com.i.lib_network.utils.Config
import com.i.lib_network.core.CommonHeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects
import java.util.concurrent.TimeUnit

class HttpRequestHelper private constructor() {
    private var connectTimeOut: Long = 0L
    private var readTimeOut: Long = 0L
    private var writeTimeOut: Long = 0L

    private var baseUrl: String = ""
    private var client: OkHttpClient? = null

    private var callAdapterFactoryList: List<CallAdapter.Factory>? = null
    private var converterFactoryList: List<Converter.Factory>? = null
    private var interceptors: List<Interceptor>? = null

    private var commonHeaderInterceptor: CommonHeaderInterceptor? = null
    private var commonRequestHeaderMap: ArrayMap<String, String>? = null

    fun <T> getApi(service: Class<T>): T {
        return getRetrofit().create(service)
    }

    private fun getRetrofit(): Retrofit {
        val builder = Retrofit.Builder().baseUrl(baseUrl)
        builder.client(client ?: getOkHttpClient())
        // 数据解析器
        val mConverterFactoryList = converterFactoryList
        if (!mConverterFactoryList.isNullOrEmpty()) {
            for (factory in mConverterFactoryList) {
                builder.addConverterFactory(factory)
            }
        } else {
            builder.addConverterFactory(GsonConverterFactory.create())
        }
        // 请求转换器
        val mCallAdapterFactoryList = callAdapterFactoryList
        if (!mCallAdapterFactoryList.isNullOrEmpty()) {
            for (factory in mCallAdapterFactoryList) {
                builder.addCallAdapterFactory(factory)
            }
        }
        return builder.build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val mInterceptors = interceptors
        builder.run {
            // interceptor
            val mCommonInterceptor = CommonHeaderInterceptor(commonRequestHeaderMap)
            commonHeaderInterceptor = mCommonInterceptor
            addInterceptor(mCommonInterceptor)
            if (mInterceptors != null) {
                for (interceptor in mInterceptors) {
                    addInterceptor(interceptor)
                }
            }
            if (Config.useReleaseLog) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
            }
            connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS)
            readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
            writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
            retryOnConnectionFailure(true)
        }
        return builder.build()
    }

    fun addCommonRequestHeader(key: String, value: String) {
        this.commonRequestHeaderMap?.let {
            it[key] = value
        } ?: let {
            val map = ArrayMap<String, String>()
            map[key] = value
            this.commonRequestHeaderMap = map
        }
        commonHeaderInterceptor?.setParams(commonRequestHeaderMap)
    }

    fun addCommonRequestHeader(map: Map<String, String>) {
        this.commonRequestHeaderMap?.let {
            map.forEach { (k, v) ->
                it[k] = v
            }
        } ?: let {
            val arrayMap = ArrayMap<String, String>()
            map.forEach { (k, v) ->
                arrayMap[k] = v
            }
            this.commonRequestHeaderMap = arrayMap
        }
        commonHeaderInterceptor?.setParams(commonRequestHeaderMap)
    }

    fun clearCommonRequestHeader() {
        commonRequestHeaderMap?.clear()
        commonHeaderInterceptor?.setParams(commonRequestHeaderMap)
    }

    class Builder {
        private var connectTimeOut = Config.DEFAULT_TIMEOUT
        private var readTimeOut = Config.DEFAULT_TIMEOUT
        private var writeTimeOut = Config.DEFAULT_TIMEOUT
        private var baseUrl: String? = null
        private var client: OkHttpClient? = null
        private var callAdapterFactoryList: List<CallAdapter.Factory>? = null
        private var converterFactoryList: List<Converter.Factory>? = null
        private var interceptors: List<Interceptor>? = null
        private var commonRequestHeaderMap: ArrayMap<String, String>? = null

        fun setConnectTimeOut(timeOut: Long): Builder {
            this.connectTimeOut = timeOut
            return this
        }

        fun setReadTimeOut(timeOut: Long): Builder {
            this.readTimeOut = timeOut
            return this
        }

        fun setWriteTimeOut(timeOut: Long): Builder {
            this.writeTimeOut = timeOut
            return this
        }

        fun setTimeOut(connectTimeOut: Long, readTimeOut: Long, writeTimeOut: Long): Builder {
            this.connectTimeOut = connectTimeOut
            this.readTimeOut = readTimeOut
            this.writeTimeOut = writeTimeOut
            return this
        }

        fun setBaseUrl(url: String): Builder {
            this.baseUrl = url
            return this
        }

        fun setOkHttpClient(client: OkHttpClient): Builder {
            this.client = client
            return this
        }

        fun setCallAdapterFactory(callAdapterFactory: CallAdapter.Factory): Builder {
            return setCallAdapterFactory(mutableListOf(callAdapterFactory))
        }

        fun setCallAdapterFactory(callAdapterFactoryList: List<CallAdapter.Factory>): Builder {
            this.callAdapterFactoryList = callAdapterFactoryList
            return this
        }

        fun setConverterFactory(converterFactory: Converter.Factory): Builder {
            return setConverterFactory(mutableListOf(converterFactory))
        }

        fun setConverterFactory(converterFactoryList: List<Converter.Factory>): Builder {
            this.converterFactoryList = converterFactoryList
            return this
        }

        fun setInterceptor(interceptor: Interceptor): Builder {
            return setInterceptor(mutableListOf(interceptor))
        }

        fun setInterceptor(interceptors: List<Interceptor>): Builder {
            this.interceptors = interceptors
            return this
        }

        fun setCommonRequestHeader(key: String, value: String): Builder {
            this.commonRequestHeaderMap?.let {
                it[key] = value
            } ?: let {
                val map = ArrayMap<String, String>()
                map[key] = value
                this.commonRequestHeaderMap = map
            }
            return this
        }

        fun setCommonRequestHeader(map: Map<String, String>): Builder {
            this.commonRequestHeaderMap?.let {
                map.forEach { (k, v) ->
                    it[k] = v
                }
            } ?: let {
                val arrayMap = ArrayMap<String, String>()
                map.forEach { (k, v) ->
                    arrayMap[k] = v
                }
                this.commonRequestHeaderMap = arrayMap
            }
            return this
        }

        fun build(): HttpRequestHelper {
            val netHelper = HttpRequestHelper()
            netHelper.connectTimeOut = connectTimeOut
            netHelper.readTimeOut = readTimeOut
            netHelper.writeTimeOut = writeTimeOut
            Objects.requireNonNull(baseUrl, "baseUrl = null")
            netHelper.baseUrl = baseUrl!!
            netHelper.client = client
            netHelper.callAdapterFactoryList = callAdapterFactoryList
            netHelper.converterFactoryList = converterFactoryList
            netHelper.interceptors = interceptors
            netHelper.commonRequestHeaderMap = commonRequestHeaderMap
            return netHelper
        }

    }
}