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
     * 透明度 0.00-1.00 之间，暂时不用，没有透明度变化的需求
     */
    private float alpha;

    /**
     * 粒子初始位置的角度
     */
    private double anger;
    private float distance = 0.5F;
    private float velocity;
    private int num = 0;

    /**
     * 重新初始化粒子
     * @param random
     * @param viewRadius
     */
    public void init(Random random, float viewRadius) {
        anger = Math.toRadians(random.nextInt(360));
        velocity = random.nextFloat();
        radius = random.nextInt(5) + 2;
        mX = (float) (viewRadius * Math.cos(anger));
        mY = (float) (viewRadius * Math.sin(anger));
    }

    /**
     * 计算下一步粒子
     * @param random
     * @param viewRadius
     */
    public void updatePoint(Random random, float viewRadius) {
        mX = (float) (mX - distance * Math.cos(anger) * velocity);
        mY = (float) (mY - distance * Math.sin(anger) * velocity);
        num++;
        //如果到了最大值 则重新给运动粒子一个轨迹属性
        if (velocity * num > 230 || num > 800) {
            num = 0;
            init(random, viewRadius);
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
