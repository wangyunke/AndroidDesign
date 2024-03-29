package com.i.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface RequestApi {

    @Streaming
    @GET
    fun download(@Url url: String): Call<ResponseBody>

}