package com.cyq.bluetooth

import android.app.Service
import android.bluetooth.*
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/4/8 10:18
 */
//蓝牙已连接
const val ACTION_GATT_CONNECTED = "com.cyq.bluetooth.BleService.ACTION_GATT_CONNECTED"

//蓝牙断开
const val ACTION_GATT_DISCONNECTED = "com.cyq.bluetooth.BleService.ACTION_GATT_DISCONNECTED"

//发现gatt服务
const val ACTION_GATT_SERVICE_DISCOVER =
    "com.cyq.bluetooth.BleService.ACTION_GATT_SERVICE_DISCOVERED"

//收到蓝牙数据
const val ACTION_DATA_AVAILABLE = "com.cyq.bluetooth.BleService.ACTION_DATA_AVAILABLE"

//连接失败
const val ACTION_CONNECTING_FAIL = "com.cyq.bluetooth.BleService.ACTION_CONNECTING_FAIL"

//蓝牙数据
const val ACTION_EXTRA_DATA = "com.cyq.bluetooth.BleService.ACTION_EXTRA_DATA"
class BleService : Service() {
    private val TAG = BleService::javaClass.name
    private lateinit var mBluetoothGatt: BluetoothGatt

    //蓝牙链接状态
    private var mConnectionState = 0

    companion object {
        //蓝牙未连接
        const val STATE_DISCONNECT = 0x001

        // 蓝牙正在链接
        const val STATE_CONNECTING = 0x002

        //蓝牙已连接
        const val STATE_CONNECTED = 0x003

        //服务标识
        val SERVER_UUID = UUID.fromString("0000ace0-0000-1000-8000-00805f9b34fb")

        //特征标识（读取数据）
        val CHARACTERISTIC_READ_UUID =
            UUID.fromString("0000ace0-0001-1000-8000-00805f9b34fb")

        //特征标识（发送数据）
        val CHARACTERISTIC_WRITE_UUID =
            UUID.fromString("0000ace0-0003-1000-8000-00805f9b34fb")

        //描述标识
        val DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")


    }


    private val mBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        val service: BleService
            get() = this@BleService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        release()
        return super.onUnbind(intent)
    }

    private val mGattCallback: BluetoothGattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                //蓝牙连接
                mConnectionState = STATE_CONNECTED
                sendBleBroadcast(ACTION_GATT_CONNECTED)
                //搜索gatt服务
                mBluetoothGatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                //蓝牙已断开连接
                mConnectionState = STATE_DISCONNECT
                sendBleBroadcast(ACTION_GATT_DISCONNECTED)
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            // 发现GATT服务
            if (status == BluetoothGatt.GATT_SUCCESS) {
                setBleNotification()
            }
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            //接收到数据
            characteristic?.let { sendBleBroadcast(ACTION_DATA_AVAILABLE, it) }
        }
    }

    /**
     * 设置蓝牙设备在数据改变时通知APP
     */
    private fun setBleNotification() {
        val gattService = mBluetoothGatt.getService(SERVER_UUID) ?: return
        val gattCharacteristic = gattService.getCharacteristic(CHARACTERISTIC_READ_UUID)
        val descriptor = gattCharacteristic.getDescriptor(DESCRIPTOR_UUID)
        descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
        if (mBluetoothGatt.writeDescriptor(descriptor)) {
            mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true)
        }
    }

    /**
     * 发送蓝牙数据
     */
    fun setData(bytes: ByteArray): Boolean {
        val gattServer = mBluetoothGatt.getService(SERVER_UUID)
        val gattCharacteristic = gattServer.getCharacteristic(CHARACTERISTIC_WRITE_UUID)
        gattCharacteristic.value = bytes
        return mBluetoothGatt.writeCharacteristic(gattCharacteristic)
    }

    /**
     * 发送广播通知蓝牙和gatt连接状态
     */
    private fun sendBleBroadcast(
        action: String,
        characteristic: BluetoothGattCharacteristic? = null
    ) {
        val intent = Intent(action)
        if (characteristic == null) {
            sendBroadcast(intent)
        } else if (CHARACTERISTIC_READ_UUID == characteristic.uuid) {
            intent.putExtra(ACTION_EXTRA_DATA, characteristic.value)
        }
    }

    /**
     * 连接蓝牙
     */
    fun connect(bluetoothAdapter: BluetoothAdapter, address: String): Boolean {
        val device = bluetoothAdapter.getRemoteDevice(address)
        device.let {
            mBluetoothGatt = device.connectGatt(this, false, mGattCallback)
            mConnectionState = STATE_CONNECTING
            return true
        }
    }

    /**
     * 断开连接
     */
    fun disconnect() {
        mBluetoothGatt.disconnect()
    }


     fun release() {
        mBluetoothGatt.close()
    }

}