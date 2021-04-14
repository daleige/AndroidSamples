package com.cyq.common

import android.util.Log
import com.cyq.bluetooth.LampView

/**
 * @describe 灯状态对应的16进制字符串转换
 * @author chenyq113@midea.com
 * @time 2021/4/14 11:46
 */
class LampBean {
    //01-开灯  02=关灯
    var isOpen: Boolean = false
    //01=默认色，02=红色，03=黄色，04=绿色
    var mColorType = LampView.Color.DEFAULT

    fun setColor(colorType: LampView.Color) {
        mColorType = colorType
        isOpen = true
    }

    fun openLamp() {
        isOpen = true
        mColorType = LampView.Color.DEFAULT
    }

    fun closeLamp() {
        isOpen = false
        mColorType = LampView.Color.BLACK
    }

    /**
     * 获取对应的16进制命令字符串
     */
    fun getCommandStr(): String {
        val strOne = if (isOpen) {
            "01"
        } else {
            "11"
        }

        val strTow = when (mColorType) {
            LampView.Color.DEFAULT -> "01"
            LampView.Color.RED -> "02"
            LampView.Color.YELLOW -> "03"
            LampView.Color.GREEN -> "04"
            else -> "01"
        }
        return strOne + strTow
    }

    /**
     * 16进制命令转换成对应的实体类状态
     */
    fun setCommandStr(command: String) {
        val strOne = command.substring(0, 2)
        val strTow = command.substring(2, 4)
        Log.d("test", "strOne:$strOne   \bstrTow$strTow")
        isOpen = strOne == "01"

        mColorType = when (strTow) {
            "01" -> LampView.Color.DEFAULT
            "02" -> LampView.Color.RED
            "03" -> LampView.Color.YELLOW
            "04" -> LampView.Color.GREEN
            else -> LampView.Color.DEFAULT
        }
    }
}