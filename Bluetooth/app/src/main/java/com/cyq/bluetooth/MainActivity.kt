package com.cyq.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "BLE"
    private val mPermission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN
    )
    private lateinit var mBleAdapter: BluetoothAdapter
    private var mBluetoothDeviceList: MutableList<BluetoothDevice> = mutableListOf()
    private var mAdapter: MyAdapter? = null

    /**
     * 搜索蓝牙结果回调
     */
    private val mLeScanCallback = LeScanCallback { bluetoothDevice, rssi, bytes ->
        run {
            if (!mBluetoothDeviceList.contains(bluetoothDevice)) {
                Log.d(TAG, "搜索到蓝牙设备：${bluetoothDevice.address}")
                mBluetoothDeviceList.add(bluetoothDevice)
                mAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearchBLE.setOnClickListener(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MyAdapter(mBluetoothDeviceList)
        mRecyclerView.adapter = mAdapter
        initBle()
    }

    private fun initBle() {
        ActivityCompat.requestPermissions(this, mPermission, 100)
        mBleAdapter = BluetoothAdapter.getDefaultAdapter()

        if (!mBleAdapter.isEnabled) {
            mBleAdapter.enable()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSearchBLE -> searchBleDevice()
        }
    }

    /**
     * 搜索蓝牙设备
     */
    private fun searchBleDevice() {
        mBleAdapter.stopLeScan(mLeScanCallback)
        mBleAdapter.startLeScan(mLeScanCallback)
        Handler().postDelayed(Runnable {
            mBleAdapter.stopLeScan(mLeScanCallback)
        }, 10_000)
    }
}