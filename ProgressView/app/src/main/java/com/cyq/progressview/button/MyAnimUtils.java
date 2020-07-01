package com.cyq.progressview.button;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * @author : ChenYangQi
 * date   : 2020/4/23 17:36
 * desc   : 创建常用动画对象
 */
public class MyAnimUtils {
    /**
     * 点击态插值器
     */
    private static final PathInterpolator CLICK_INTERPOLATOR = new PathInterpolator(0.57F, 0.01f, 0.41F, 1.00F);

    /**
     * 温度环呼吸动效插值器
     */
    public static final PathInterpolator BREATH_INTERPOLATOR = new PathInterpolator(0.40F, 0.00F, 0.60F, 1.00F);

    /**
     * 按钮底部弹出时的插值器
     */
    public static final PathInterpolator BUTTON_SHELL_INTERPOLATOR = new PathInterpolator(0.57F, 0.01F, 0.41F, 1.00F);

    /**
     * 首页时钟按下的Scale插值器
     */
    public static final PathInterpolator FRAGMENT1_CLOCK_SCALE_INTERPOLATOR = new PathInterpolator(0.4F, 0F, 0.6F, 1F);

    /**
     * 按钮点击态放大动画
     *
     * @return
     */
    public static ScaleAnimation getClickScaleAnim() {
        ScaleAnimation clickScaleAnim = new ScaleAnimation(1f, 1.05f, 1f, 1.05f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickScaleAnim.setDuration(200);
        clickScaleAnim.setFillAfter(true);
        clickScaleAnim.setRepeatCount(1);
        clickScaleAnim.setInterpolator(CLICK_INTERPOLATOR);
        clickScaleAnim.setRepeatMode(Animation.REVERSE);
        return clickScaleAnim;
    }

    /**
     * 按钮点击态变色动画
     *
     * @param touchColor
     * @param pressedColor
     * @return
     */
    public static ValueAnimator getClickColorAnim(int touchColor, int pressedColor) {
        ValueAnimator clickColorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), touchColor, pressedColor);
        clickColorAnim.setDuration(200);
        clickColorAnim.setRepeatCount(1);
        clickColorAnim.setInterpolator(CLICK_INTERPOLATOR);
        clickColorAnim.setRepeatMode(ValueAnimator.REVERSE);
        return clickColorAnim;
    }

//    public static ValueAnimator getActionDownAnim(){
//        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), touchColor, pressedColor);
//        animator.setDuration(200);
//        animator.setRepeatCount(1);
//        animator.setInterpolator(CLICK_INTERPOLATOR);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        return animator;
//    }

    /**
     * 打开进入的错帧动画
     *
     * @return
     */
    public static LayoutAnimationController getOpenEnterLAController() {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.4F,
                Animation.RELATIVE_TO_PARENT, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F);
        animation.setDuration(250);
        animation.setFillAfter(true);
        //自定义插值器
        animation.setInterpolator(new PathInterpolator(0.0F, 0.0F, 0.4F, 1.0F));

        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        controller.setDelay(0.02F);
        return controller;
    }

    /**
     * 打开退出的错帧动画
     *
     * @return
     */
    public static LayoutAnimationController getOpenExitLAController() {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0F,
                Animation.RELATIVE_TO_PARENT, -0.4F,
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F);
        animation.setDuration(150);
        animation.setFillAfter(true);
        //自定义插值器
        animation.setInterpolator(new PathInterpolator(0.8F, 0.0F, 0.8F, 1F));

        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        controller.setDelay(0.0333333F);
        return controller;
    }

    /**
     * 关闭进入的错帧动画
     *
     * @return
     */
    public static LayoutAnimationController getCloseEnterLAController() {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.4F,
                Animation.RELATIVE_TO_PARENT, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F);
        animation.setDuration(250);
        animation.setFillAfter(true);
        //自定义插值器
        animation.setInterpolator(new PathInterpolator(0.0F, 0.0F, 0.4F, 1.0F));

        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        controller.setDelay(0.02F);
        return controller;
    }

    /**
     * 关闭退出的错帧动画
     *
     * @return
     */
    public static LayoutAnimationController getCloseExitLAController() {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0F,
                Animation.RELATIVE_TO_PARENT, 0.4F,
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, 0.0F);
        animation.setDuration(150);
        animation.setFillAfter(true);
        //自定义插值器
        animation.setInterpolator(new PathInterpolator(0.8F, 0.0F, 0.8F, 1F));

        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        controller.setDelay(0.0333333F);
        return controller;
    }

}
