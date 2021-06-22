package src.com.listener;

public interface IBleDeviceService {

    void startListener(BleListener listener);

    void stopListener(BleListener listener);
}
