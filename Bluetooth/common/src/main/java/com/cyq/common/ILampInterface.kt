package com.cyq.common

import com.cyq.common.LampView

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/4/9 11:23
 */
interface ILampInterface {
    fun open()

    fun close()

    fun setLampColor(color: LampView.Color)
}