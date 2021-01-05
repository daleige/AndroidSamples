package src.com.cyq.design.构建者模式.经典版建造者模式;

public class Computer {
    /**
     * 设备名称，必选
     */
    private final String deviceName;
    /**
     * 可选参数
     */
    private String cpu;
    private String ram;
    private String deviceId;
    private String productId;
    private String systemType;

    public Computer(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    @Override
    public String toString() {
        return "主机信息\n" +
                "设备名称：" + deviceName + '\n' +
                "处理器：" + cpu + '\n' +
                "内存：" + ram + '\n' +
                "设备ID：" + deviceId + '\n' +
                "产品ID：" + productId + '\n' +
                "系统类型：" + systemType + '\n';
    }
}
