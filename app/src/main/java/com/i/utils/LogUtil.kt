package com.i.utils

import android.util.Log
import com.i.designpattern.BuildConfig

object LogUtil {
    private val DEFAULT_LOGGER = Logger("LogUtil")

    @JvmStatic
    fun v(tag: String, message: String, vararg args: Any?) {
        DEFAULT_LOGGER.tag(tag).v(message, *args)
    }

    @JvmStatic
    fun d(tag: String, message: String, vararg args: Any?) {
        DEFAULT_LOGGER.tag(tag).d(message, *args)
    }

    @JvmStatic
    fun i(tag: String, message: String, vararg args: Any?) {
        DEFAULT_LOGGER.tag(tag).i(message, *args)
    }

    @JvmStatic
    fun w(tag: String, message: String, vararg args: Any?) {
        DEFAULT_LOGGER.tag(tag).w(message, *args)
    }

    fun e(tag: String, message: String, vararg args: Any?) {
        DEFAULT_LOGGER.tag(tag).e(message, *args)
    }

    @JvmStatic
    fun e(tag: String, message: String, e: Throwable) {
        DEFAULT_LOGGER.tag(tag).e(message, e)
    }

    fun wtf(tag: String, message: String, vararg args: Any?) {
        DEFAULT_LOGGER.tag(tag).wtf(message, *args)
    }

    @JvmStatic
    fun wtf(tag: String, e: Throwable) {
        DEFAULT_LOGGER.tag(tag).wtf(e)
    }

    class Logger(private var logTag: String) {
        // adb shell setprop log.tag.LogUtil D  或者
        // 修改/data/local.prop文件 log.tag.LogUtil=D

        private val isVerboseLoggable: Boolean
            get() = DEBUG || Log.isLoggable(logTag, Log.VERBOSE)

        private val isDebugLoggable: Boolean
            get() = DEBUG || Log.isLoggable(logTag, Log.DEBUG)

        private val isInfoLoggable: Boolean
            get() = DEBUG || Log.isLoggable(logTag, Log.INFO)

        private val isWarnLoggable: Boolean
            get() = DEBUG || Log.isLoggable(logTag, Log.WARN)

        private val isErrorLoggable: Boolean
            get() = DEBUG || Log.isLoggable(logTag, Log.ERROR)

        private val isWtfLoggable: Boolean
            get() = DEBUG || Log.isLoggable(logTag, Log.ASSERT)

        fun tag(tag: String): Logger {
            logTag = tag
            return this
        }

        fun v(message: String, vararg args: Any?) {
            if (isVerboseLoggable) {
                Log.v(
                    logTag, if (args.isEmpty() || args[0] == null) {
                        message
                    } else {
                        String.format(message, *args)
                    }
                )
            }
        }

        fun d(message: String, vararg args: Any?) {
            if (isDebugLoggable) {
                Log.d(
                    logTag, if (args.isEmpty() || args[0] == null) {
                        message
                    } else {
                        String.format(message, *args)
                    }
                )
            }
        }

        fun i(message: String, vararg args: Any?) {
            if (isInfoLoggable) {
                Log.i(
                    logTag, if (args.isEmpty() || args[0] == null) {
                        message
                    } else {
                        String.format(message, *args)
                    }
                )
            }
        }

        fun w(message: String, vararg args: Any?) {
            if (isWarnLoggable) {
                Log.w(
                    logTag, if (args.isEmpty() || args[0] == null) {
                        message
                    } else {
                        String.format(message, *args)
                    }
                )
            }
        }

        fun e(message: String, vararg args: Any?) {
            if (isErrorLoggable) {
                Log.e(
                    logTag, if (args.isEmpty() || args[0] == null) {
                        message
                    } else {
                        String.format(message, *args)
                    }
                )
            }
        }

        fun e(message: String, e: Throwable) {
            if (isErrorLoggable) {
                Log.e(logTag, message, e)
            }
        }

        fun wtf(message: String, vararg args: Any?) {
            if (isWtfLoggable) {
                Log.wtf(
                    logTag, if (args.isEmpty() || args[0] == null) {
                        message
                    } else {
                        String.format(message, *args)
                    }
                )
            }
        }

        fun wtf(e: Throwable) {
            if (isWtfLoggable) {
                Log.wtf(logTag, e)
            }
        }

        companion object {
            val DEBUG = BuildConfig.DEBUG
        }
    }
}