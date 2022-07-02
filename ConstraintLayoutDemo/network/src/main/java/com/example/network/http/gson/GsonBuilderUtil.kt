package com.example.network.http.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonBuilderUtil {

    val gson: Gson = GsonBuilder().apply {
        //registerTypeAdapter(MasRsp::class.java, MasJsonParser())
        registerTypeAdapter(String::class.java, NullStringAdapter())
        registerTypeAdapter(Integer::class.java, IntegerDefault0Adapter())
        registerTypeAdapter(Int::class.java, IntegerDefault0Adapter())
        registerTypeAdapter(Double::class.java, DoubleDefault0Adapter())
        registerTypeAdapter(Long::class.java, LongDefault0Adapter())
    }.create()
}