package com.cyq.peripheral

import android.Manifest
import android.bluetooth.*
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ParcelUuid
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "peripheral"

        //服务标识
        val serviceData: ByteArray = "0000ace000001000800000805f9b34fb".toByteArray()

        //特征标识（读取数据）
        val CHARACTERISTIC_READ_UUID =
            UUID.fromString("0000ace0-0001-1000-8000-00805f9b34fb")

        //特征标识（发送数据）
        val CHARACTERISTIC_WRITE_UUID =
            UUID.fromString("0000ace0-0001-1000-8000-00805f9b34f6")

        //描述标识
        val DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
    }

    private val mPermission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val mBluetoothAdapter: BluetoothAdapter
        get() {
            return BluetoothAdapter.getDefaultAdapter()
        }
    private lateinit var mBluetoothManager: BluetoothManager
    private lateinit var mBluetoothGattServer: BluetoothGattServer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenLamp.setOnClickListener(this)
        btnCloseLamp.setOnClickListener(this)
        btnYellowLamp.setOnClickListener(this)
        btnGreenLamp.setOnClickListener(this)
        switchView.setOnCheckedChangeListener { _, isChecked -> tagger(isChecked) }
        btnOpenAdvertise.setOnClickListener(this)
        ActivityCompat.requestPermissions(this, mPermission, 100)
    }


    /**
     * 开启广播
     */
    private fun startBluetoothAdvertise() {
        //广播参数设置
        val setting = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER)
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
            .setTimeout(0)
            .setConnectable(true)
            .build()

        //广播包
        val advertiseData = AdvertiseData.Builder()
            //是否在广播中携带设备名称
            .setIncludeDeviceName(true)
            //是否在广播中携带信号强度
            .setIncludeTxPowerLevel(true)
            .build()

        //扫描回应的广播设置
        val scanResponseData = AdvertiseData.Builder()
            .setIncludeDeviceName(false)
            .setIncludeTxPowerLevel(true)
            //.addServiceData(ParcelUuid(CHARACTERISTIC_WRITE_UUID), serviceData)
            //设置厂商数据
            .addManufacturerData(0x11, serviceData)
            .build()

        //设置名称
        //mBluetoothAdapter.name = "peripheral_lamp_0001"
        //开启广播
        val bluetoothLeAdvertiser = mBluetoothAdapter.bluetoothLeAdvertiser
        if (bluetoothLeAdvertiser == null) {
            Log.d(TAG, "该手机不支持BLE广播")
        } else {
            //bluetoothLeAdvertiser.stopAdvertising(mAdvertiseCallback)
            bluetoothLeAdvertiser.startAdvertising(
                setting,
                advertiseData,
                null,
                mAdvertiseCallback
            )
        }
    }

    private val mAdvertiseCallback = object : AdvertiseCallback() {
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
            super.onStartSuccess(settingsInEffect)
            Log.d(TAG, "开启广播成功")
        }

        override fun onStartFailure(errorCode: Int) {
            super.onStartFailure(errorCode)
            Log.d(TAG, "开启广播失败！")
            Log.d(TAG, "广播失败：$errorCode")
        }
    }

    /**
     * service事件回调
     */
    private val mBluetoothGattServerCallback = object : BluetoothGattServerCallback() {

        override fun onCharacteristicReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicReadRequest(device, requestId, offset, characteristic)
            mBluetoothGattServer.sendResponse(
                device,
                requestId,
                BluetoothGatt.GATT_SUCCESS,
                offset,
                characteristic?.value
            )
        }

        override fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            mBluetoothGattServer.sendResponse(
                device,
                requestId,
                BluetoothGatt.GATT_SUCCESS,
                offset,
                value
            )
        }

        override fun onDescriptorReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            descriptor: BluetoothGattDescriptor?
        ) {
            mBluetoothGattServer.sendResponse(
                device,
                requestId,
                BluetoothGatt.GATT_SUCCESS,
                offset,
                null
            )
        }

        override fun onDescriptorWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            descriptor: BluetoothGattDescriptor?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            mBluetoothGattServer.sendResponse(
                device,
                requestId,
                BluetoothGatt.GATT_SUCCESS,
                offset,
                value
            );
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenLamp -> lampView.open()
            R.id.btnCloseLamp -> lampView.close()
            R.id.btnGreenLamp -> lampView.setLampColor(LampView.Color.GREEN)
            R.id.btnYellowLamp -> lampView.setLampColor(LampView.Color.YELLOW)
            R.id.btnOpenAdvertise -> {
                if (mBluetoothAdapter.isEnabled) {
                    //先关闭广播
                    startBluetoothAdvertise()
                }
            }
        }
    }


    private fun tagger(isChecked: Boolean) {
        if (isChecked) {
            mBluetoothAdapter.enable()
            Toast.makeText(this@MainActivity, "蓝牙已打开", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                mBluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
                mBluetoothGattServer =
                    mBluetoothManager.openGattServer(this, mBluetoothGattServerCallback)
            }, 3_000)
        } else {
            mBluetoothAdapter.disable()
            Toast.makeText(this@MainActivity, "蓝牙已关闭", Toast.LENGTH_SHORT).show()
        }
    }
}