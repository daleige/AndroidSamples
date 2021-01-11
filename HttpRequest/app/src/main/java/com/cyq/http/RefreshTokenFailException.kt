package com.cyq.http

import kotlin.Exception

/**
 * @describe Token刷新失败异常
 * @author chenyq113@midea.com
 * @time 2021/1/11 11:41
 */
class RefreshTokenFailException(message: String?) : Exception(message) {
}