package com.i.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.util.*
import kotlin.Exception

internal object JsonUtil {

    private val gson: Gson = Gson()

    private fun getJsonString(any: Any?): String? {
        return try {
            gson.toJson(any)
        } catch (e: Exception) {
            null
        }
    }

    fun getJsonObject(any: Any?): JsonObject {
        val str = getJsonString(any)
        return JsonParser.parseString(str).asJsonObject
    }

    fun <T> jsonToObject(jsonString: String?, cls: Class<T>?): T? {
        return try {
            gson.fromJson(jsonString, cls)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> jsonToList(json: String?, cls: Class<T>?): MutableList<T> {
        val gson = Gson()
        val list: MutableList<T> = ArrayList()
        json?.let {
            try {
                val array = JsonParser.parseString(it).asJsonArray
                for (elem in array) {
                    list.add(gson.fromJson(elem, cls))
                }
            } catch (e: Exception) {
            }
        }
        return list
    }
}

internal inline fun <reified T> Gson.json2Bean(json: String?): T? {
    return Gson().fromJson(json, T::class.java)
}