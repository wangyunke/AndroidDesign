package com.i.lib_network.utils

import android.util.Log

internal object LogUtil {

    private const val tag = "network_library"

    private val isDebug = false

    fun iTag(dTag: String, vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.I)) {
            return
        }
        if (contents.size == 1) {
            Log.d(dTag, contents[0].toString())
        } else {
            val strBuilder = StringBuilder()
            for (i in contents.indices) {
                strBuilder.append(contents[i]?.toString() ?: "null")
                if (i != contents.size - 1) {
                    strBuilder.append(", ")
                }
            }
            Log.i(dTag, strBuilder.toString())
        }
    }

    fun i(vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.I)) {
            return
        }
        iTag(tag, *contents)
    }

    fun dTag(dTag: String, vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.D)) {
            return
        }
        if (contents.size == 1) {
            Log.d(dTag, contents[0].toString())
        } else {
            val strBuilder = StringBuilder()
            for (i in contents.indices) {
                strBuilder.append(contents[i]?.toString() ?: "null")
                if (i != contents.size - 1) {
                    strBuilder.append(", ")
                }
            }
            Log.d(dTag, strBuilder.toString())
        }
    }

    fun d(vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.D)) {
            return
        }
        dTag(tag, *contents)
    }

    fun wTag(wTag: String, vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.W)) {
            return
        }
        if (contents.size == 1) {
            Log.w(wTag, contents[0].toString())
        } else {
            val strBuilder = StringBuilder()
            for (i in contents.indices) {
                strBuilder.append(contents[i]?.toString() ?: "null")
                if (i != contents.size - 1) {
                    strBuilder.append(", ")
                }
            }
            Log.w(wTag, strBuilder.toString())
        }
    }

    fun w(vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.W)) {
            return
        }
        wTag(tag, *contents)
    }

    fun eTag(eTag: String, vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.E)) {
            return
        }
        if (contents.size == 1) {
            Log.e(eTag, contents[0].toString())
        } else {
            val strBuilder = StringBuilder()
            for (i in contents.indices) {
                strBuilder.append(contents[i]?.toString() ?: "null")
                if (i != contents.size - 1) {
                    strBuilder.append(", ")
                }
            }
            Log.e(eTag, strBuilder.toString())
        }
    }

    fun e(vararg contents: Any?) {
        if (!checkPrintLog(Config.LogLevel.E)) {
            return
        }
        eTag(tag, *contents)
    }

    private fun checkPrintLog(logLevel: Config.LogLevel): Boolean {
        // build type
        if (isDebug) {
            if (!Config.useDebugLog) {
                return false
            }
        } else {
            if (!Config.useReleaseLog) {
                return false
            }
        }

        // log level
        return if (Config.logLevel == Config.LogLevel.NONE) {
            true
        } else if (Config.logLevel == Config.LogLevel.CLOSE) {
            false
        } else logLevel.ordinal >= Config.logLevel.ordinal
    }

}