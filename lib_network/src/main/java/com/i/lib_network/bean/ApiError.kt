package com.i.lib_network.bean

object ApiError {

    /**
     * 未知错误的code
     */
    const val unknownErrorCode = 9000

    /**
     * 网络异常的code
     */
    const val netErrorCode = 9001

    /**
     * 服务端异常的code
     */
    const val serverErrorCode = 9002

    /**
     * 数据为空的异常code
     */
    const val emptyDataErrorCode = 9003

    /**
     * 解析数据失败的异常code
     */
    const val parseDataErrorCode = 9004

    /**
     * 未知异常
     */
    val unknownError = ErrorBean(unknownErrorCode, "unKnown error", null)

    /**
     * 网络异常
     */
    val netError = ErrorBean(netErrorCode, "network error", null)

    /**
     * 服务端异常
     */
    val serverError = ErrorBean(serverErrorCode, "server status error", null)

    /**
     * 数据解析异常
     */
    val parseError = ErrorBean(serverErrorCode, "response data parse error", null)

}

