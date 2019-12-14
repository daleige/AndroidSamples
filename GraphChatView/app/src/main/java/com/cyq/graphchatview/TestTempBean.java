package com.cyq.graphchatview;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/14 11:51
 * desc   :
 */
public class TestTempBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * actualTemperature : 039
         * addTime : 2013-01-18 16:53:42
         * probeTemperature : 038
         */

        private String actualTemperature;
        private String addTime;
        private String probeTemperature;

        public String getActualTemperature() {
            return actualTemperature;
        }

        public void setActualTemperature(String actualTemperature) {
            this.actualTemperature = actualTemperature;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getProbeTemperature() {
            return probeTemperature;
        }

        public void setProbeTemperature(String probeTemperature) {
            this.probeTemperature = probeTemperature;
        }
    }
}
