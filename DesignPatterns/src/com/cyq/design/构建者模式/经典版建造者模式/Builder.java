package src.com.cyq.design.构建者模式.经典版建造者模式;

public abstract class Builder {
    public abstract void setCpu();

    public abstract void setRam();

    public abstract void setDeviceId();

    public abstract void setProductId();

    public abstract void setSystemType();

    public abstract Computer buildComputer();
}
