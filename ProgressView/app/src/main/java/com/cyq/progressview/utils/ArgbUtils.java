package com.cyq.progressview.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;

import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.cyq.progressview.evaluator.ProgressParameter;

/**
 * @author : ChenYangQi
 * date   : 2020/7/2 13:53
 * desc   : 颜色值计算的工具类
 */
public class ArgbUtils {
    private static final ArgbUtils sInstance = new ArgbUtils();
    private ProgressParameter mParameter = new ProgressParameter();

    public static ArgbUtils getInstance() {
        return sInstance;
    }

    private static int insideColor1 = Color.parseColor("#FF001BFF");
    private static int outsizeColor1 = Color.parseColor("#A60067FF");
    private static int progressColor1 = Color.parseColor("#FF0066FF");
    private static int pointColor1 = Color.parseColor("#FF1978FF");
    private static int bgCircleColor1 = Color.parseColor("#290066FF");
    private static int insideColor2 = Color.parseColor("#FFFFB600");
    private static int outsizeColor2 = Color.parseColor("#A6FFB600");
    private static int progressColor2 = Color.parseColor("#FFFFDB00");
    private static int pointColor2 = Color.parseColor("#FFFFBD00");
    private static int bgCircleColor2 = Color.parseColor("#1AFFDB00");
    private static int insideColor3 = Color.parseColor("#FFFF8700");
    private static int outsizeColor3 = Color.parseColor("#A6FF7700");
    private static int progressColor3 = Color.parseColor("#FFFFA300");
    private static int pointColor3 = Color.parseColor("#FFFF9700");
    private static int bgCircleColor3 = Color.parseColor("#1AFFA300");
    private static int insideColor4 = Color.parseColor("#FFFF2200");
    private static int outsizeColor4 = Color.parseColor("#A6FF1600");
    private static int progressColor4 = Color.parseColor("#FFFF8000");
    private static int pointColor4 = Color.parseColor("#FFFF5500");
    private static int bgCircleColor4 = Color.parseColor("#1AFF5500");

    /**
     * 获取当前进度值（0~3600）时对应的颜色值
     *
     * @param progressValue 0~3600之间的圆环进度值
     * @return
     */
    @SuppressLint("RestrictedApi")
    public ProgressParameter getProgressParameter(float lastTimeValue, float progressValue) {
        float fraction = progressValue % 1200 / 1200;
        Log.e("test", "fraction---->" + fraction);
        if (progressValue < 1200) {
            //第一个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor1, insideColor2));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor1, outsizeColor2));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor1, progressColor2));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor1, pointColor2));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor1, bgCircleColor2));
        } else if (progressValue < 2400) {
            //第二个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor2, insideColor3));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor2, outsizeColor3));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor2, progressColor3));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor2, pointColor3));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor2, bgCircleColor3));
        } else if (progressValue < 3600) {
            //第三个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor3, insideColor4));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor3, outsizeColor4));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor3, progressColor4));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor3, pointColor4));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor3, bgCircleColor4));
        }
        return mParameter;
    }
}
