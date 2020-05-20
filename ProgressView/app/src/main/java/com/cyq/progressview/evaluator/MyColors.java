package com.cyq.progressview.evaluator;

/**
 * @author : ChenYangQi
 * date   : 2020/5/20 17:43
 * desc   : 自定义动画属性，适用于进度圆进度变化时几种颜色的变化规律
 */
public class MyColors {
    /**
     * 外圈颜色
     */
    private int outColor;

    /**
     * 渐变色开始颜色，渐变圈外圈颜色
     */
    private int beginColor;

    /**
     * 渐变色结束颜色，渐变圈内圈颜色
     */
    private int endColor;

    public MyColors() {
    }

    public MyColors(int outColor, int beginColor, int endColor) {
        this.outColor = outColor;
        this.beginColor = beginColor;
        this.endColor = endColor;
    }

    public int getOutColor() {
        return outColor;
    }

    public void setOutColor(int outColor) {
        this.outColor = outColor;
    }

    public int getBeginColor() {
        return beginColor;
    }

    public void setBeginColor(int beginColor) {
        this.beginColor = beginColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }
}
