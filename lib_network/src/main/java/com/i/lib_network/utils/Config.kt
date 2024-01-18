package com.i.lib_network.utils

object Config {

    const val DEFAULT_TIMEOUT = 10000L
    const val RESPONSE_CODE_SUCCESS = 0

    var useDebugLog = true
    var useReleaseLog = false
    var logLevel: LogLevel = LogLevel.NONE
    enum class LogLevel {
        I, D, W, E,
        NONE,
        CLOSE
    }

}