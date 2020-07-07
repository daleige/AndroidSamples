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
     * 粒子初始位置的角度
     */
    private double anger;
    /**
     * 一帧移动的速度
     */
    private float velocity;
    /**
     * 总共移动的帧数
     */
    private int num = 0;

    /**
     * 透明度80~100
     */
    private float alpha = 0.8F;

    /**
     * 随机偏移角度
     */
    private double randomAnger = 0;

    /**
     * 重新初始化粒子
     *
     * @param random
     * @param viewRadius
     */
    public void init(Random random, float viewRadius) {
        anger = Math.toRadians(random.nextInt(360));
        velocity = random.nextFloat() * 1.6F;
        radius = random.nextInt(6) + 2;
        mX = (float) (viewRadius * Math.cos(anger));
        mY = (float) (viewRadius * Math.sin(anger));
        //随机偏移角度-30°~30°
        randomAnger = Math.toRadians(30 - random.nextInt(60));
    }

    /**
     * 计算下一步粒子
     *
     * @param random
     * @param viewRadius
     */
    public void updatePoint(Random random, float viewRadius) {
        //每一帧偏移的像素大小
        float distance = 1F;
        double moveAnger = anger + randomAnger;
        mX = (float) (mX - distance * Math.cos(moveAnger) * velocity);
        mY = (float) (mY - distance * Math.sin(moveAnger) * velocity);
        //模拟半径逐渐变小
        radius = radius - 0.01F;
        num++;
        //如果到了最大值 则重新给运动粒子一个轨迹属性
        int maxDistance = 120;
        int maxNum = 700;
        if (velocity * num > maxDistance || num > maxNum) {
            num = 0;
            init(random, viewRadius);
        }
    }

    /**
     * 避免重复申请内存，从现有对象clone获得新对象
     *
     * @return
     */
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

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getRandomAnger() {
        return randomAnger;
    }

    public void setRandomAnger(double randomAnger) {
        this.randomAnger = randomAnger;
    }
}
