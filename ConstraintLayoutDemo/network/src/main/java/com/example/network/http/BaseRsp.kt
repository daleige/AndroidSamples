package com.example.network.http

data class BaseRsp<T>(var data: T? = null, var errorMsg: String = "", var errorCode: Int = 0) {

    fun isSuccess(): Boolean {
        return errorCode == 0
    }
}
