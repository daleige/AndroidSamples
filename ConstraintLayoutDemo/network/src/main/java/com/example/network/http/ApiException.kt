package com.example.network.http

import java.lang.Exception

class ApiException(private val errorCode: Int, var errorMsg: String?) : Exception() {

    override fun toString(): String {
        var msg = "null"
        errorMsg?.let { msg = it }
        return "errorCode:$errorCode, errorMsg:$msg"
    }
}