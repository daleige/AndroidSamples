package com.cyq.progressview.widget;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 15:47
 * desc   : 用于表示运动粒子的属性.实现Cloneable避免粒子对象重复创建
 */
public class Point implements Cloneable {
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
    protected Point clone() {
        Point point = null;
        try {
            point = (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return point;
    }

}
