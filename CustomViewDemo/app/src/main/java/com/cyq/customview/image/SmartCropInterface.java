package com.cyq.customview.image;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.print.PrintManager;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/4/20 17:49
 */
public interface SmartCropInterface {

    /**
     * 获取顶点的坐标
     *
     * @return 资格顶点的Pont
     */
    Point[] getPoints();

    /**
     * 裁剪
     */
    void crop();

    /**
     * 顺时针旋转
     */
    void clockwiseRotate();

    /**
     * 逆时针旋转
     */
    void anticlockwiseRotate();
}
