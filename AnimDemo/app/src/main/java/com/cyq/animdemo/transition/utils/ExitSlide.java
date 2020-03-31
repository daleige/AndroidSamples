package com.cyq.animdemo.transition.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import static com.cyq.animdemo.transition.utils.Constant.DURATION;

/**
 * @author : ChenYangQi
 * date   : 2020/3/31 14:18
 * desc   : 自定义Slide
 */
public class ExitSlide extends Transition {
    private static final String TOP = "top";
    private static final String RIGHT = "RIGHT";
    private static final String LEFT = "left";
    private static final String BOTTOM = "bottom";
    private Rect rect;

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        rect = new Rect();
        view.getHitRect(rect);
        transitionValues.values.put(LEFT, rect.left);
        transitionValues.values.put(TOP, rect.top);
        transitionValues.values.put(RIGHT, rect.right);
        transitionValues.values.put(BOTTOM, rect.bottom);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        transitionValues.values.put(LEFT, rect.width());
        transitionValues.values.put(RIGHT, rect.width() * 2);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        View endView = endValues.view;
        ObjectAnimator animator = ObjectAnimator.ofFloat(endView, "translationX", 0, -rect.width())
                .setDuration(DURATION);
        animator.setInterpolator(new DecelerateInterpolator());
        return animator;
    }
}
