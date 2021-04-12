package com.cyq.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.*
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyq.bluetooth.MyAdapter.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "BLE"
    }

    private val mPermission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN
    )
    private lateinit var mBleAdapter: BluetoothAdapter
    private var mBluetoothDeviceList: MutableList<BluetoothDevice> = mutableListOf()
    private var mAdapter: MyAdapter? = null
    private lateinit var mBleReceiver: BroadcastReceiver
    private var mBleService: BleService? = null

    private var mScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            val bluetoothDevice = result.device
            if (!TextUtils.isEmpty(bluetoothDevice.name) && !mBluetoothDeviceList.contains(
                    bluetoothDevice
                )
            ) {
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
        mAdapter?.itemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                Toast.makeText(this@MainActivity, "开始连接", Toast.LENGTH_SHORT).show()
                mBleAdapter.bluetoothLeScanner.stopScan(mScanCallback)
                mBleService?.connect(mBleAdapter, mBluetoothDeviceList[position].address)
            }
        }
        mRecyclerView.adapter = mAdapter
        initBle()
        //注册蓝牙信息接收器
        registerBleReceived()
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

    private fun registerBleReceived() {
        val intent = Intent(this, BleService::class.java)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        startService(intent)

        //注册蓝牙接收器的广播
        val filter = IntentFilter()
        filter.addAction(ACTION_GATT_CONNECTED)
        filter.addAction(ACTION_GATT_DISCONNECTED)
        filter.addAction(ACTION_GATT_SERVICE_DISCOVER)
        filter.addAction(ACTION_DATA_AVAILABLE)
        filter.addAction(ACTION_CONNECTING_FAIL)
        mBleReceiver = BleReceiver()
        registerReceiver(mBleReceiver, filter)
    }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBleService = (service as BleService.LocalBinder).service
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBleService = null
        }
    }

    /**
     * 搜索蓝牙设备
     */
    private fun searchBleDevice() {
        mBleAdapter.bluetoothLeScanner.stopScan(mScanCallback)
        //设置扫描参数
        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()
        mBleAdapter.bluetoothLeScanner.startScan(null, scanSettings, mScanCallback)

        Handler(Looper.getMainLooper()).postDelayed({
            mBleAdapter.bluetoothLeScanner.stopScan(mScanCallback)
        }, 30_000)
    }

    inner class BleReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (getIntent().action) {
                ACTION_GATT_CONNECTED ->
                    Toast.makeText(this@MainActivity, "蓝牙已连接", Toast.LENGTH_SHORT).show()
                ACTION_GATT_DISCONNECTED -> {
                    Toast.makeText(this@MainActivity, "蓝牙已断开", Toast.LENGTH_SHORT).show()
                    mBleService?.release()
                }
                ACTION_CONNECTING_FAIL -> {
                    Toast.makeText(this@MainActivity, "蓝牙已断开", Toast.LENGTH_SHORT).show()
                    mBleService?.disconnect()
                }
                ACTION_DATA_AVAILABLE -> {
                    val byteArray = intent?.getByteArrayExtra(ACTION_EXTRA_DATA)
                    Log.d(TAG, "收到的数据：$byteArray")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBleReceiver)
        unbindService(mServiceConnection)
        mBleService = null
    }
}