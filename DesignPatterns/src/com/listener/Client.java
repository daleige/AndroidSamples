package src.com.listener;

public class Client {
    public static void main(String[] args) {
        BleListener a = new A();
        IBleDeviceService server = new BleDeviceServer();
        server.startListener(a);


        //
        server.stopListener(a);
    }
}
