package src.com.cyq.design.构建者模式.经典版建造者模式;

public class LenovoBuilder extends Builder {
    private final Computer computer ;

    public LenovoBuilder(String deviceName) {
        computer = new Computer(deviceName);
    }

    @Override
    public void setCpu() {
        computer.setCpu("Intel(R) Core(TM)i7-10510U CPU1.8Hz~2.3Hz");
    }

    @Override
    public void setRam() {
        computer.setRam("16GB");
    }

    @Override
    public void setDeviceId() {
        computer.setDeviceId("AS87AD7C-SDGF-SDDV-7DUY8655SD");
    }

    @Override
    public void setProductId() {
        computer.setProductId("00425-00000-00002-AA474");
    }

    @Override
    public void setSystemType() {
        computer.setSystemType("Microsoft Windows 10 企业版 LTSC");
    }

    @Override
    public Computer buildComputer() {
        return computer;
    }
}
