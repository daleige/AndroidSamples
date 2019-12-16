package com.cyq.graphchatview;

/**
 * @author : ChenYangQi
 * date   : 2019/12/11 16:50
 * desc   : Shadow定义的温度和时间
 */
public class TempBean {
    /**
     * 时间戳
     */
    public long timestamp;
    /**
     * 温度
     */
    public int temp;


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
