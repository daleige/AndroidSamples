package src.com.listener;

public class BleDeviceServer implements IBleDeviceService {


    @Override
    public void startListener(BleListener listener) {
        for (int i = 0; i < 100; i++) {
            listener.onScannerDevice("---------" + i);
        }
    }

    @Override
    public void stopListener(BleListener listener) {

    }
}
