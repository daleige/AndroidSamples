package src.com.cyq.design.构建者模式.经典版建造者模式;

public abstract class AbstractBuilder {
    public abstract void setCpu(String cpu);

    public abstract void setRam(String ram);

    public abstract void setDeviceId(String deviceId);

    public abstract void setProductId(String productId);

    public abstract void setSystemType(String systemType);

    public abstract Computer buildComputer();
}
