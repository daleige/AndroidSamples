package com.example.ble.ble

import com.example.ble.ble.bean.Packager
import com.example.ble.ble.callback.BleConnectStateListener
import com.example.ble.ble.callback.BleOpenStateListener
import com.example.ble.ble.callback.IBleManager

/**
 * @author:YangQi.Chen
 * @date:2023/7/2 13:12
 * @description:蓝牙库功能管理类
 */
object BleManager : IBleManager {

    override fun openBle() {

    }

    override fun closeBle() {

    }

    override fun connectBle(Address: String, connectStateListener: BleConnectStateListener?) {

    }

    override fun sendMessage(bleAddress: String, packager: Packager) {

    }

    override fun registerBleOpenStateListener(listener: BleOpenStateListener) {
    }

    override fun registerBleConnectStateListener(listener: BleConnectStateListener) {
    }

    override fun removeBleOpenStateListener(listener: BleOpenStateListener) {
    }

    override fun removeBleConnectStateListener(listener: BleConnectStateListener) {
    }
}