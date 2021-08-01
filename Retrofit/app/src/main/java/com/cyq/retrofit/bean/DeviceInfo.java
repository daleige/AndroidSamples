package com.cyq.retrofit.bean;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/6 16:06
 */
public class DeviceInfo {


    /**
     * code : 200
     * description : success
     * data : {"name":"美的空调","time":"2021年1月1日","deviceId":"12123123"}
     */

    private int code;
    private String description;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 美的空调
         * time : 2021年1月1日
         * deviceId : 12123123
         */

        private String name;
        private String time;
        private String deviceId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", time='" + time + '\'' +
                    ", deviceId='" + deviceId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
