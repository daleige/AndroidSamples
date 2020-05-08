package com.cyq.progressview.widget;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 15:47
 * desc   : 动画粒子.实现Cloneable避免粒子对象重复创建
 */
public class AnimPoint implements Cloneable {
    /**
     * 粒子原点x坐标
     */
    private int centerX;
    /**
     * 粒子原点y坐标
     */
    private int centerY;
    /**
     * 粒子半径
     */
    private int radius;
    /**
     * 透明度 0.00-1.00 之间
     */
    private float alpha;

    /**
     * 粒子初始位置的角度
     */
    private double anger;




    public double getAnger() {
        return anger;
    }

    public void setAnger(double anger) {
        this.anger = anger;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
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
