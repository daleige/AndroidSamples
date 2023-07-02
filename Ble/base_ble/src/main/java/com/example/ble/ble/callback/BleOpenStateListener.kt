package com.example.ble.ble.callback

/**
 * @author:YangQi.Chen
 * @date :2023/7/2 13:00
 * @description:蓝牙开关状态监听
 */
interface BleOpenStateListener {
    /**
     * 蓝牙开关打开
     */
    fun onBleOpen()

    /**
     * 蓝牙开关关闭
     */
    fun onBleClose()
}