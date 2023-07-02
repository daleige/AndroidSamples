package com.example.ble.ble.callback

/**
 * @author:YangQi.Chen
 * @date :2023/7/2 13:00
 * @description:蓝牙连接状态和数据监听
 */
interface BleConnectStateListener {

    /**
     * 接收到蓝牙数据
     */
    fun onReceiveMessage()

    /**
     * 连接蓝牙成功
     */
    fun onConnectSuccess(bleAddress: String)

    /**
     * 连接蓝牙失败
     */
    fun onConnectFail(bleAddress: String, errorCode: Int)
}