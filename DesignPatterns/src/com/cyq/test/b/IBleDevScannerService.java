package src.com.cyq.test.b;

public interface IBleDevScannerService {

    void scanBleDevice(OnBleDevScanCallback callback);

    void stopScan();


    interface  OnBleDevScanCallback{
        void onStartListener();

        void onStopListener();

        void onScannerDevice();
    }
}
