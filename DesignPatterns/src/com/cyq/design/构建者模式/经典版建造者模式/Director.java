package src.com.cyq.design.构建者模式.经典版建造者模式;

public class Director {

    public void makeComputer(AbstractBuilder builder) {
        builder.setCpu("Intel(R) Core(TM)i7-10510U CPU1.8Hz~2.3Hz");
        builder.setDeviceId("AS87AD7C-SDGF-SDDV-7DUY8655SD");
        builder.setProductId("00425-00000-00002-AA474");
        builder.setRam("16GB");
        builder.setSystemType("Microsoft Windows 10 企业版 LTSC");
    }
}
