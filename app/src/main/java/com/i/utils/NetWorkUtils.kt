package com.i.utils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import java.io.IOException
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.SocketException
import java.net.URL

internal object NetWorkUtils {

    var NET_CNNT_BAIDU_OK = 1 // NetworkAvailable
    var NET_CNNT_BAIDU_TIMEOUT = 2 // no NetworkAvailable
    var NET_NOT_PREPARE = 3 // Net no ready
    var NET_ERROR = 4 //net error
    private val TIMEOUT = 3000 // TIMEOUT

    /**
     * check NetworkAvailable
     *
     * @param context
     * @return
     */
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val manager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo

        return !(null == info || !info.isAvailable)
    }

    /**
     * check NetworkConnected
     *
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context): Boolean {
        val manager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return !(null == info || !info.isConnected)
    }

    fun isNetworkConnected(): Boolean {
        return true
    }

    /**
     * 得到ip地址
     *
     * @return
     */
    @JvmStatic
    fun getLocalIpAddress(): String {
        var ret = ""
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val enumIpAddress = en.nextElement().inetAddresses
                while (enumIpAddress.hasMoreElements()) {
                    val netAddress = enumIpAddress.nextElement()
                    if (!netAddress.isLoopbackAddress) {
                        ret = netAddress.hostAddress.toString()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }

        return ret
    }


    /**
     * ping "http://www.baidu.com"
     *
     * @return
     */
    @JvmStatic
    private fun pingNetWork(): Boolean {
        var result = false
        var httpUrl: HttpURLConnection? = null
        try {
            httpUrl = URL("http://www.baidu.com")
                .openConnection() as HttpURLConnection
            httpUrl.connectTimeout = TIMEOUT
            httpUrl.connect()
            result = true
        } catch (e: IOException) {
        } finally {
            if (null != httpUrl) {
                httpUrl.disconnect()
            }
        }
        return result
    }

    /**
     * check is3G
     *
     * @param context
     * @return boolean
     */
    @JvmStatic
    fun is3G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * isWifi
     *
     * @param context
     * @return boolean
     */
    @JvmStatic
    fun isWifi(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * is2G
     *
     * @param context
     * @return boolean
     */
    @JvmStatic
    fun is2G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && (activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EDGE
                || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
            .subtype == TelephonyManager.NETWORK_TYPE_CDMA)
    }

    /**
     * 判断MOBILE网络是否可用
     */
    fun isMobile(context: Context?): Boolean {
        if (context != null) {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (null != networkInfo && networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                return networkInfo.isAvailable
        }
        return false
    }

    private fun hasInternetAccess(): Boolean {
        return try {
            val url = URL("https://www.baidu.com")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            LogUtil.i("NetWorkUtils", "responseCode1=${connection.responseCode}")
            connection.responseCode == 200
        } catch (e: Exception) {
            LogUtil.e("NetWorkUtils", "hasInternetAccess=${e.message}")
            false
        }
    }
}