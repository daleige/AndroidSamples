package com.example.ble.ble.callback

import com.example.ble.ble.bean.Packager


/**
 * @author:YangQi.Chen
 * @date :2023/7/2 13:00
 * @description:蓝牙库对外暴露功能定义
 */
interface IBleManager {

    /**
     * 打开蓝牙开关
     */
    fun openBle()

    /**
     * 关闭蓝牙开关
     */
    fun closeBle()

    /**
     * 连接蓝牙，并注册连接结果和数据接收结果的回调
     */
    fun connectBle(Address: String, connectStateListener: BleConnectStateListener? = null)

    /**
     * 发送数据
     */
    fun sendMessage(bleAddress: String, packager: Packager)

    /**
     * 注册蓝牙开关状态的监听
     */
    fun registerBleOpenStateListener(listener: BleOpenStateListener)

    /**
     * 注册蓝牙连接状态和接受数据的监听
     */
    fun registerBleConnectStateListener(listener: BleConnectStateListener)

    /**
     * 移除蓝牙开关状态的监听
     */
    fun removeBleOpenStateListener(listener: BleOpenStateListener)

    /**
     * 移除蓝牙连接状态和接受数据的监听
     */
    fun removeBleConnectStateListener(listener: BleConnectStateListener)
}

