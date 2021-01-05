package src.com.cyq.design.构建者模式;

public class Director {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("张三的电脑")
                .setCpu("Intel(R) Core(TM)i7-10510U CPU1.8Hz~2.3Hz")
                .setRam("16GB")
                .setDeviceId("AS87AD7C-SDGF-SDDV-7DUY8655SD")
                .setProductId("00425-00000-00002-AA474")
                .setSystemType("Microsoft Windows 10 企业版 LTSC")
                .build();
        System.out.println(computer.toString());
    }
}
