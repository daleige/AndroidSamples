package com.cyq.library;

/**
 * Time: 2019-12-07 23:30
 * Author: ChenYangQi
 * Description:
 */
public class ScaleBean {

    /**
     * 刻度的文字
     */
    private String scale;

    /**
     * 刻度的四种状态
     */
    private ScaleType type;

    /**
     * 面包图片的4种状态
     */
    private BreadType breadType;

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public ScaleType getType() {
        return type;
    }

    public void setType(ScaleType type) {
        this.type = type;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }
}
