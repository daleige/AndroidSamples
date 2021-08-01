package src.com.listener;

public class A implements BleListener {


    @Override
    public void onScannerDevice(Object obj) {
        System.out.println(obj.toString());
    }

}
