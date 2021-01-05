package src.com.cyq.design.构建者模式.经典版建造者模式;

public class LenovoBuilder extends AbstractBuilder {
    private final Computer computer ;

    public LenovoBuilder(String deviceName) {
        computer = new Computer(deviceName);
    }

    @Override
    public void setCpu(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void setRam(String ram) {
        computer.setRam(ram);
    }

    @Override
    public void setDeviceId(String deviceId) {
        computer.setDeviceId(deviceId);
    }

    @Override
    public void setProductId(String productId) {
        computer.setProductId(productId);
    }

    @Override
    public void setSystemType(String systemType) {
        computer.setSystemType(systemType);
    }

    @Override
    public Computer buildComputer() {
        return computer;
    }
}
