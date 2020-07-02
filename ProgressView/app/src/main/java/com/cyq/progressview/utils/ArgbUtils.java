package com.cyq.progressview.utils;

import android.graphics.Color;

import com.cyq.progressview.evaluator.ProgressParameter;

/**
 * @author : ChenYangQi
 * date   : 2020/7/2 13:53
 * desc   : 颜色值计算的工具类
 */
public class ArgbUtils {
    private static final ArgbUtils sInstance = new ArgbUtils();
    private ProgressParameter mParameter;

    public static ArgbUtils getInstance() {
        return sInstance;
    }

    private int insideColor1 = Color.parseColor("#FF001BFF");
    private int outsizeColor1 = Color.parseColor("#A60067FF");
    private int progressColor1 = Color.parseColor("#FF0066FF");
    private int pointColor1 = Color.parseColor("#FF1978FF");
    private int bgCircleColor1 = Color.parseColor("#290066FF");
    private int insideColor2 = Color.parseColor("#FFFFB600");
    private int outsizeColor2 = Color.parseColor("#A6FFB600");
    private int progressColor2 = Color.parseColor("#FFFFDB00");
    private int pointColor2 = Color.parseColor("#FFFFBD00");
    private int bgCircleColor2 = Color.parseColor("#1AFFDB00");
    private int insideColor3 = Color.parseColor("#FFFF8700");
    private int outsizeColor3 = Color.parseColor("#A6FF7700");
    private int progressColor3 = Color.parseColor("#FFFFA300");
    private int pointColor3 = Color.parseColor("#FFFF9700");
    private int bgCircleColor3 = Color.parseColor("#1AFFA300");
    private int insideColor4 = Color.parseColor("#FFFF2200");
    private int outsizeColor4 = Color.parseColor("#A6FF1600");
    private int progressColor4 = Color.parseColor("#FFFF8000");
    private int pointColor4 = Color.parseColor("#FFFF5500");
    private int bgCircleColor4 = Color.parseColor("#1AFF5500");

    private ProgressParameter progressParameter1 = new ProgressParameter(0, insideColor1, outsizeColor1, progressColor1, pointColor1, bgCircleColor1);
    private ProgressParameter progressParameter2 = new ProgressParameter(3600 / 3, insideColor2, outsizeColor2, progressColor2, pointColor2, bgCircleColor2);
    private ProgressParameter progressParameter3 = new ProgressParameter(3600 / 3 * 2, insideColor3, outsizeColor3, progressColor3, pointColor3, bgCircleColor3);
    private ProgressParameter progressParameter4 = new ProgressParameter(3600, insideColor4, outsizeColor4, progressColor4, pointColor4, bgCircleColor4);

    /**
     * 获取当前进度值（0~3600）时对应的颜色值
     *
     * @param progressValue 0~3600之间的圆环进度值
     * @return
     */
    public ProgressParameter getProgressParameter(float progressValue) {
        mParameter = new ProgressParameter();
        float fraction = progressValue / 3600;
        if (progressValue < 1200) {
            //第一个颜色段
            mParameter.setInsideColor(evaluate(fraction, progressParameter1.getInsideColor(),
                    progressParameter2.getInsideColor()));
            mParameter.setOutsizeColor(evaluate(fraction, progressParameter1.getOutsizeColor(),
                    progressParameter2.getOutsizeColor()));
            mParameter.setProgressColor(evaluate(fraction, progressParameter1.getProgressColor(),
                    progressParameter2.getProgressColor()));
            mParameter.setPointColor(evaluate(fraction, progressParameter1.getPointColor(),
                    progressParameter2.getPointColor()));
            mParameter.setBgCircleColor(evaluate(fraction, progressParameter1.getBgCircleColor(),
                    progressParameter2.getBgCircleColor()));
        } else if (progressValue < 2400) {
            //第二个颜色段
            mParameter.setInsideColor(evaluate(fraction, progressParameter2.getInsideColor(),
                    progressParameter3.getInsideColor()));
            mParameter.setOutsizeColor(evaluate(fraction, progressParameter2.getOutsizeColor(),
                    progressParameter3.getOutsizeColor()));
            mParameter.setProgressColor(evaluate(fraction, progressParameter2.getProgressColor(),
                    progressParameter3.getProgressColor()));
            mParameter.setPointColor(evaluate(fraction, progressParameter2.getPointColor(),
                    progressParameter3.getPointColor()));
            mParameter.setBgCircleColor(evaluate(fraction, progressParameter2.getBgCircleColor(),
                    progressParameter3.getBgCircleColor()));
        } else {
            //第三个颜色段
            mParameter.setInsideColor(evaluate(fraction, progressParameter3.getInsideColor(),
                    progressParameter4.getInsideColor()));
            mParameter.setOutsizeColor(evaluate(fraction, progressParameter3.getOutsizeColor(),
                    progressParameter4.getOutsizeColor()));
            mParameter.setProgressColor(evaluate(fraction, progressParameter3.getProgressColor(),
                    progressParameter4.getProgressColor()));
            mParameter.setPointColor(evaluate(fraction, progressParameter3.getPointColor(),
                    progressParameter4.getPointColor()));
            mParameter.setBgCircleColor(evaluate(fraction, progressParameter3.getBgCircleColor(),
                    progressParameter4.getBgCircleColor()));
        }
        return mParameter;
    }

    /**
     * 根据fraction的值获取两端颜色重的具体位置颜色值
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    public int evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        float startA = ((startInt >> 24) & 0xff) / 255.0f;
        float startR = ((startInt >> 16) & 0xff) / 255.0f;
        float startG = ((startInt >> 8) & 0xff) / 255.0f;
        float startB = (startInt & 0xff) / 255.0f;

        int endInt = (Integer) endValue;
        float endA = ((endInt >> 24) & 0xff) / 255.0f;
        float endR = ((endInt >> 16) & 0xff) / 255.0f;
        float endG = ((endInt >> 8) & 0xff) / 255.0f;
        float endB = (endInt & 0xff) / 255.0f;

        // convert from sRGB to linear
        startR = (float) Math.pow(startR, 2.2);
        startG = (float) Math.pow(startG, 2.2);
        startB = (float) Math.pow(startB, 2.2);

        endR = (float) Math.pow(endR, 2.2);
        endG = (float) Math.pow(endG, 2.2);
        endB = (float) Math.pow(endB, 2.2);

        // compute the interpolated color in linear space
        float a = startA + fraction * (endA - startA);
        float r = startR + fraction * (endR - startR);
        float g = startG + fraction * (endG - startG);
        float b = startB + fraction * (endB - startB);

        // convert back to sRGB in the [0..255] range
        a = a * 255.0f;
        r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
        g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
        b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;

        return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
    }
}
