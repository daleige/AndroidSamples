package com.cyq.progressview.evaluator;

/**
 * @author : ChenYangQi
 * date   : 2020/5/20 17:43
 * desc   : 自定义动画属性，适用于进度圆进度变化时几种颜色的变化规律
 */
public class ProgressColors {
    private int progress;

    /**
     * 内发光，也就是圆形渐变色的最外圈颜色
     */
    private int insideColor;

    /**
     * 外发光
     */
    private int outsizeColor;

    /**
     * 进度环颜色
     */
    private int progressColor;

    /**
     * 运动粒子颜色
     */
    private int pointColor;

    /**
     * 底色圆环颜色
     */
    private int bgCircleColor;

    public ProgressColors() {
    }

    public ProgressColors(int progress, int insideColor, int outsizeColor, int progressColor, int pointColor, int bgCircleColor) {
        this.insideColor = insideColor;
        this.outsizeColor = outsizeColor;
        this.progressColor = progressColor;
        this.pointColor = pointColor;
        this.bgCircleColor = bgCircleColor;
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getBgCircleColor() {
        return bgCircleColor;
    }

    public void setBgCircleColor(int bgCircleColor) {
        this.bgCircleColor = bgCircleColor;
    }

    public int getInsideColor() {
        return insideColor;
    }

    public void setInsideColor(int insideColor) {
        this.insideColor = insideColor;
    }

    public int getOutsizeColor() {
        return outsizeColor;
    }

    public void setOutsizeColor(int outsizeColor) {
        this.outsizeColor = outsizeColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }
}
