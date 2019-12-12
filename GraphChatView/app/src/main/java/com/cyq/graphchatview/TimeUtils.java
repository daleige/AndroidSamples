package com.cyq.graphchatview;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/11 17:36
 * desc   : 温度曲线的时间轴数字/单位变化处理工具类
 */
public class TimeUtils {
    public static XRollerInfo getXRollerInfo(List<TempBean> list) {
        if (list.size() <= 0) {
            return null;
        }
        long timeDifference = list.get(list.size() - 1).getTimestamp() - list.get(0).getTimestamp();
        //得到时间差也就是时长
        int millis = (int) (timeDifference / 60);

        if (millis < 1) {
            return new XRollerInfo(1.5f, "0", "0.5", "1", "分");
        } else if (millis < 2) {
            return new XRollerInfo(1.5f, "0", "0.5", "1", "分");
        } else if (millis < 4) {
            return new XRollerInfo(3, "0", "1", "2", "分");
        } else if (millis < 6) {
            return new XRollerInfo(6, "0", "2", "4", "分");
        } else if (millis < 8) {
            return new XRollerInfo(9, "0", "3", "6", "分");
        } else if (millis < 10) {
            return new XRollerInfo(12, "0", "4", "8", "分");
        } else if (millis < 12) {
            return new XRollerInfo(15, "0", "5", "10", "分");
        } else if (millis < 20) {
            return new XRollerInfo(18, "0", "6", "12", "分");
        } else if (millis < 30) {
            return new XRollerInfo(30, "0", "10", "20", "分");
        } else if (millis < 40) {
            return new XRollerInfo(45, "0", "15", "30", "分");
        } else if (millis < 50) {
            return new XRollerInfo(60, "0", "20", "40", "分");
        } else if (millis < 60) {
            return new XRollerInfo(75, "0", "25", "50", "分");
        } else if (millis < 70) {
            return new XRollerInfo(90, "0", "30", "60", "分");
        } else if (millis < 80) {
            return new XRollerInfo(105, "0", "35", "70", "分");
        } else if (millis < 100) {
            return new XRollerInfo(120, "0", "40", "80", "分");
        } else if (millis < 120) {
            return new XRollerInfo(150, "0", "50", "100", "分");
        } else if (millis < 240) {
            return new XRollerInfo(180, "0", "1", "2", "小时");
        } else if (millis < 480) {
            return new XRollerInfo(360, "0", "2", "4", "小时");
        } else if (millis < 720) {
            return new XRollerInfo(720, "0", "4", "8", "小时");
        } else if (millis < 960) {
            return new XRollerInfo(1080, "0", "6", "12", "小时");
        } else if (millis < 1200) {
            return new XRollerInfo(1440, "0", "8", "16", "小时");
        } else if (millis < 1440) {
            return new XRollerInfo(1800, "0", "10", "20", "小时");
        } else if (millis < 2160) {
            return new XRollerInfo(2160, "0", "12", "24", "小时");
        } else if (millis < 2880) {
            return new XRollerInfo(3240, "0", "18", "36", "小时");
        }
        return null;
    }

    /**
     * X轴信息
     */
    public static class XRollerInfo {
        public XRollerInfo(float maxMillis, String firstStr, String secondStr, String threeStr, String typeStr) {
            this.firstStr = firstStr;
            this.secondStr = secondStr;
            this.threeStr = threeStr;
            this.typeStr = typeStr;
            this.maxMillis = maxMillis;
        }

        float maxMillis;
        String firstStr;
        String secondStr;
        String threeStr;
        String typeStr;

        public float getMaxMillis() {
            return maxMillis;
        }

        public String getFirstStr() {
            return firstStr;
        }

        public String getSecondStr() {
            return secondStr;
        }

        public String getThreeStr() {
            return threeStr;
        }

        public String getTypeStr() {
            return typeStr;
        }

        @NonNull
        @Override
        public String toString() {
            return firstStr + "--" + secondStr + "--" + threeStr + typeStr + typeStr + maxMillis;
        }
    }
}
