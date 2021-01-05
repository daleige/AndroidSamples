package src.com.cyq.design.构建者模式.经典版建造者模式;

public class Director {

    public void makeComputer(Builder builder) {
        builder.setCpu();
        builder.setDeviceId();
        builder.setProductId();
        builder.setRam();
        builder.setSystemType();
    }
}
