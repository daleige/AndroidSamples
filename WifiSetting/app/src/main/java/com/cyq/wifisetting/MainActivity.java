package com.cyq.wifisetting;

import android.Manifest;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.wifilibrary.WiFiManager;
import com.cyq.wifilibrary.listener.OnWifiConnectListener;
import com.cyq.wifilibrary.listener.OnWifiEnabledListener;
import com.cyq.wifilibrary.listener.OnWifiScanResultsListener;
import com.kongqw.permissionslibrary.PermissionsManager;

import java.util.List;

/**
 * @author ChenYangQi
 */
public class MainActivity extends AppCompatActivity implements OnWifiEnabledListener,
        OnWifiScanResultsListener, OnWifiConnectListener {
    private static final int RC_CAMERA_AND_LOCATION = 101;
    private WiFiManager mWiFiManager;
    private PermissionsManager mPermissionsManager;
    private final int GET_WIFI_LIST_REQUEST_CODE = 0;
    String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWiFiManager = WiFiManager.getInstance(getApplicationContext());
        if (!mWiFiManager.isWifiEnabled()) {
            mWiFiManager.openWiFi();
        }
        // 动态权限管理器
        mPermissionsManager = new PermissionsManager(this) {
            @Override
            public void authorized(int requestCode) {
                // 6.0 以上系统授权通过
                if (GET_WIFI_LIST_REQUEST_CODE == requestCode) {
                    // 获取WIFI列表
                    List<ScanResult> scanResults = mWiFiManager.getScanResults();
                    Log.i("test", "scanResults:" + scanResults.size());
                }
            }

            @Override
            public void noAuthorization(int requestCode, String[] lacksPermissions) {
                // 6.0 以上系统授权失败
            }

            @Override
            public void ignore() {
                // 6.0 以下系统 获取WIFI列表
                List<ScanResult> scanResults = mWiFiManager.getScanResults();
            }
        };
        // 请求WIFI列表
        mPermissionsManager.checkPermissions(GET_WIFI_LIST_REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWiFiManager.setOnWifiEnabledListener(this);
        mWiFiManager.setOnWifiScanResultsListener(this);
        mWiFiManager.setOnWifiConnectListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 移除监听
        mWiFiManager.removeOnWifiEnabledListener();
        mWiFiManager.removeOnWifiScanResultsListener();
        mWiFiManager.removeOnWifiConnectListener();
    }

    @Override
    public void onWifiEnabled(boolean enabled) {
        Log.i("test", "WIFI 打开状态：" + enabled);
    }

    @Override
    public void onScanResults(List<ScanResult> scanResults) {
        Log.i("test", "WIFI扫描成功：" + scanResults.size());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onWiFiConnectLog(String log) {
        Log.i("test", "WIFI连接中");
    }

    @Override
    public void onWiFiConnectSuccess(String SSID) {
        Log.i("test", "WIFI连接成功");
    }

    @Override
    public void onWiFiConnectFailure(String SSID) {
        Log.i("test", "WIFI连接失败");
    }

    public void scaleWifi(View view) {
        Log.i("test", "开始刷新-----");
        mWiFiManager.startScan();
    }

    public void openWifi(View view) {
        if (mWiFiManager.isWifiEnabled()) {
            mWiFiManager.closeWiFi();
        } else {
            mWiFiManager.openWiFi();
        }
    }
}
