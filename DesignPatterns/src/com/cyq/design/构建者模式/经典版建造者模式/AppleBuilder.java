package src.com.cyq.design.构建者模式.经典版建造者模式;

public class AppleBuilder extends Builder {
    private final Computer computer ;

    public AppleBuilder(String deviceName) {
        computer = new Computer(deviceName);
    }

    @Override
    public void setCpu() {
        computer.setCpu("Intel(R) Core(TM)i9-10000 CPU2.3Hz~3.3Hz");
    }

    @Override
    public void setRam() {
        computer.setRam("32GB");
    }

    @Override
    public void setDeviceId() {
        computer.setDeviceId("234SDFBF-RGGD-SDDV-234RSEFSDF");
    }

    @Override
    public void setProductId() {
        computer.setProductId("1111-003400-234234-JH898");
    }

    @Override
    public void setSystemType() {
        computer.setSystemType("MacOS 16");
    }

    @Override
    public Computer buildComputer() {
        return computer;
    }
}
