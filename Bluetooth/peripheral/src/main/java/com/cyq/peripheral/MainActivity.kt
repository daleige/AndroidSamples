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
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "peripheral"

        //服务标识
        val UUID_SERVICE: UUID = UUID.fromString("38400000-8cf0-11bd-b23e-111111111111")

        //特征标识（读取数据）
        val UUID_READ_CHARACTERISTIC: UUID = UUID.fromString("38400000-8cf0-11bd-b23e-222222222222")

        //特征标识的描述（读取数据）
        val UUID_READ_DESCRIPTOR: UUID = UUID.fromString("38400000-8cf0-11bd-b23e-333333333333")

        //特征标识（写入数据）
        val UUID_WRITE_CHARACTERISTIC: UUID =
            UUID.fromString("38400000-8cf0-11bd-b23e-444444444444")

        //特征标识的描述（写入数据）
        val UUID_WRITE_DESCRIPTOR: UUID = UUID.fromString("38400000-8cf0-11bd-b23e-555555555555")
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
    private lateinit var mGattService: BluetoothGattService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenLamp.setOnClickListener(this)
        btnCloseLamp.setOnClickListener(this)
        btnYellowLamp.setOnClickListener(this)
        btnGreenLamp.setOnClickListener(this)
        btnRedLamp.setOnClickListener(this)
        switchView.setOnCheckedChangeListener { _, isChecked -> tagger(isChecked) }
        btnOpenAdvertise.setOnClickListener(this)
        ActivityCompat.requestPermissions(this, mPermission, 100)
    }

    /**
     * 开启广播
     */
    private fun startBluetoothAdvertise() {
        //设置名称
        mBluetoothAdapter.name = "peripheral_lamp_${Random().nextInt(9999)}"
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
//        val scanResponseData = AdvertiseData.Builder()
//            .setIncludeDeviceName(false)
//            .setIncludeTxPowerLevel(true)
//            .addServiceData(ParcelUuid(UUID_WRITE_CHARACTERISTIC), serviceData)
//            //设置厂商数据
//            .addManufacturerData(0x11, serviceData)
//            .build()

        //开启广播
        val bluetoothLeAdvertiser = mBluetoothAdapter.bluetoothLeAdvertiser
        if (bluetoothLeAdvertiser == null) {
            Log.d(TAG, "该手机不支持BLE广播")
        } else {
            bluetoothLeAdvertiser.stopAdvertising(mAdvertiseCallback)
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
    private val mGattServerCallback = object : BluetoothGattServerCallback() {

        //设备链接 or 断开链接回调
        override fun onConnectionStateChange(device: BluetoothDevice?, status: Int, newState: Int) {
            super.onConnectionStateChange(device, status, newState)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "设备：${device?.address}链接成功")
            } else {
                Log.d(TAG, "device:${device?.name}链接失败：$status")
            }
        }

        //添加本地服务回调
        override fun onServiceAdded(status: Int, service: BluetoothGattService?) {
            super.onServiceAdded(status, service)
            if (status == 0) {
                Log.d(TAG, "添加本地服务成功：" + service?.uuid)
            } else {
                Log.d(TAG, "添加本地服务失败")
            }
        }

        //特征值读取回调
        override fun onCharacteristicReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicReadRequest(device, requestId, offset, characteristic)
            Log.d(TAG, "特征值读取回调")
        }

        //特征值写入回调
        override fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            super.onCharacteristicWriteRequest(
                device,
                requestId,
                characteristic,
                preparedWrite,
                responseNeeded,
                offset,
                value
            )
            Log.d(
                TAG, "特征值写入回调:\n" +
                        "device:${device?.name} \n" +
                        "requestId:${requestId} \n" +
                        "characteristic:${characteristic?.value} \n" +
                        "preparedWrite:${preparedWrite} \n" +
                        "responseNeeded:${responseNeeded} \n" +
                        "offset:${offset} \n" +
                        "value:${value.contentToString()} \n"
            )
            Log.d(TAG, "接收到的数据为：${ByteUtil.toHexString(value)}")
        }

        //描述读取回调
        override fun onDescriptorReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            descriptor: BluetoothGattDescriptor?
        ) {
            super.onDescriptorReadRequest(device, requestId, offset, descriptor)
            Log.d(TAG, "描述读取回调")
        }

        //描述写入回调
        override fun onDescriptorWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            descriptor: BluetoothGattDescriptor?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            super.onDescriptorWriteRequest(
                device,
                requestId,
                descriptor,
                preparedWrite,
                responseNeeded,
                offset,
                value
            )
            Log.d(TAG, "描述写入回调")
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenLamp -> lampView.open()
            R.id.btnCloseLamp -> lampView.close()
            R.id.btnGreenLamp -> lampView.setLampColor(LampView.Color.GREEN)
            R.id.btnYellowLamp -> lampView.setLampColor(LampView.Color.YELLOW)
            R.id.btnRedLamp -> lampView.setLampColor(LampView.Color.RED)
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
                initGattService()
            }, 3_000)
        } else {
            mBluetoothAdapter.disable()
            Toast.makeText(this@MainActivity, "蓝牙已关闭", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 初始化服务
     */
    private fun initGattService() {
        mGattService = BluetoothGattService(
            UUID_SERVICE,
            BluetoothGattService.SERVICE_TYPE_PRIMARY
        )

        val mWriteGattCharacteristic = BluetoothGattCharacteristic(
            UUID_WRITE_CHARACTERISTIC,
            BluetoothGattCharacteristic.PROPERTY_WRITE,
            BluetoothGattCharacteristic.PERMISSION_WRITE
        )

        val mWriteGattDescriptor =
            BluetoothGattDescriptor(UUID_WRITE_DESCRIPTOR, BluetoothGattDescriptor.PERMISSION_WRITE)

        val mReadGattCharacteristic = BluetoothGattCharacteristic(
            UUID_READ_CHARACTERISTIC,
            BluetoothGattCharacteristic.PROPERTY_READ,
            BluetoothGattCharacteristic.PERMISSION_READ
        )

        val mReadGattDescriptor = BluetoothGattDescriptor(
            UUID_READ_DESCRIPTOR,
            BluetoothGattDescriptor.PERMISSION_WRITE
        )

        //添加特征值和特征值描述
        mWriteGattCharacteristic.addDescriptor(mWriteGattDescriptor)
        mReadGattCharacteristic.addDescriptor(mReadGattDescriptor)
        mGattService.addCharacteristic(mWriteGattCharacteristic)
        mGattService.addCharacteristic(mReadGattCharacteristic)

        mBluetoothGattServer = mBluetoothManager.openGattServer(this, mGattServerCallback)
        val result = mBluetoothGattServer.addService(mGattService)
        if (result) {
            Toast.makeText(this, "添加服务成功", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "添加服务失败", Toast.LENGTH_SHORT).show()
        }
    }
}