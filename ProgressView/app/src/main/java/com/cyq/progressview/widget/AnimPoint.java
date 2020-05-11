package com.cyq.progressview.widget;

import java.util.Random;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 15:47
 * desc   : 动画粒子.实现Cloneable避免粒子对象重复创建
 */
public class AnimPoint implements Cloneable {
    /**
     * 粒子原点x坐标
     */
    private float mX;
    /**
     * 粒子原点y坐标
     */
    private float mY;
    /**
     * 粒子半径
     */
    private float radius;
    /**
     * 透明度 0.00-1.00 之间
     */
    private float alpha;

    /**
     * 粒子初始位置的角度
     */
    private double anger;

    private final int liveTime = 3;
    private float velocity = 20;
    private Random mRandom = new Random();

    public void init() {
        anger = Math.toRadians(mRandom.nextInt(360));
        mX = (float) (radius * Math.cos(anger));
        mY = (float) (radius * Math.sin(anger));
    }

    public void updatePoint() {
        mX = (float) (mX - velocity * Math.cos(anger));
        mY = (float) (mY - velocity * Math.sin(anger));
        //如果到了最大值 则重新给运动粒子一个轨迹属性
        if (radius * Math.sin(anger) >= mY) {
            init();
        }
    }

    public double getAnger() {
        return anger;
    }

    public void setAnger(double anger) {
        this.anger = anger;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getmX() {
        return mX;
    }

    public void setmX(float mX) {
        this.mX = mX;
    }

    public float getmY() {
        return mY;
    }

    public void setmY(float mY) {
        this.mY = mY;
    }

    @Override
    protected AnimPoint clone() {
        AnimPoint animPoint = null;
        try {
            animPoint = (AnimPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return animPoint;
    }

}
