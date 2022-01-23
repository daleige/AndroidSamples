package com.chenyangqi.coroutines

import java.lang.Exception

/**
 * @author : ChenYangQi
 * date   : 2022/1/23 19:24
 * desc   :
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}