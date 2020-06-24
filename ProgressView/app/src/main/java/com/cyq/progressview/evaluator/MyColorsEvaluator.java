package com.cyq.progressview.evaluator;

import android.animation.TypeEvaluator;

/**
 * @author : ChenYangQi
 * date   : 2020/5/20 17:42
 * desc   : 自定义属性动画的TypeEvaluator
 */
public class MyColorsEvaluator implements TypeEvaluator<ProgressColors> {

    @Override
    public ProgressColors evaluate(float fraction, ProgressColors startValue, ProgressColors endValue) {
        ProgressColors myColors = new ProgressColors();
        myColors.setInsideColor((Integer) getCurrentColor(fraction, startValue.getInsideColor(),
                endValue.getInsideColor()));
        myColors.setOutsizeColor((Integer) getCurrentColor(fraction, startValue.getOutsizeColor(),
                endValue.getOutsizeColor()));
        myColors.setProgressColor((Integer) getCurrentColor(fraction, startValue.getProgressColor(),
                endValue.getProgressColor()));
        myColors.setPointColor((Integer) getCurrentColor(fraction, startValue.getPointColor(),
                endValue.getPointColor()));
        myColors.setBgCircleColor((Integer) getCurrentColor(fraction, startValue.getBgCircleColor(),
                endValue.getBgCircleColor()));
        return myColors;
    }

    /**
     * 计算当前的argb颜色值，实现方式参考{@link android.animation.ArgbEvaluator}
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    private Object getCurrentColor(float fraction, Object startValue, Object endValue) {
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
