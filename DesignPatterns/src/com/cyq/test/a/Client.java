package src.com.cyq.test.a;

import src.com.cyq.test.b.BleDev;
import src.com.cyq.test.b.IBleDevScannerService;

public class Client {

    public static void main(String[] args) {
        IBleDevScannerService aaa = new BleDev();
        aaa.scanBleDevice(new IBleDevScannerService.OnBleDevScanCallback() {
            @Override
            public void onStartListener() {

            }

            @Override
            public void onStopListener() {

            }

            @Override
            public void onScannerDevice() {

            }
        });

    }

}
