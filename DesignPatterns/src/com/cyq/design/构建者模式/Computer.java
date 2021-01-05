package src.com.cyq.design.构建者模式;

public class Computer {
    /**
     * 设备名称，必选
     */
    private final String deviceName;
    private final String cpu;
    private final String ram;
    private final String deviceId;
    private final String productId;
    private final String systemType;

    public Computer(Builder builder) {
        deviceName = builder.getDeviceName();
        cpu = builder.getCpu();
        ram = builder.getRam();
        deviceId = builder.getDeviceId();
        productId = builder.getProductId();
        systemType = builder.getSystemType();
    }

    public static class Builder {
        private final String deviceName;
        private String cpu;
        private String ram;
        private String deviceId;
        private String productId;
        private String systemType;

        public Builder(String deviceName) {
            this.deviceName = deviceName;
        }

        public Computer build() {
            return new Computer(this);
        }

        public String getDeviceName() {
            return deviceName;
        }

        public String getCpu() {
            return cpu;
        }

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public String getRam() {
            return ram;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public String getProductId() {
            return productId;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public String getSystemType() {
            return systemType;
        }

        public Builder setSystemType(String systemType) {
            this.systemType = systemType;
            return this;
        }
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
