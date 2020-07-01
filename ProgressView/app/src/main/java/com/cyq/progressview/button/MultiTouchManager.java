package com.cyq.progressview.button;

/**
 * @author : ChenYangQi
 * date   : 2020/3/10 9:53
 * desc   : 多点触控管理类，用于标记此时Activity是否拦截触摸事件，防止多点同时点击
 */
public class MultiTouchManager {
    public static volatile boolean disableAllClick = false;
    public static volatile boolean navigateIconDisableAllClick = false;
    /**
     * 列表滑动的场景下有个延时处理down时间，用此tag标记是否执行down事件延时结束时是否更新UI
     */
    public static volatile boolean actionDownTag;

    /**
     * 列表滑动的场景下有个延时处理down时间 ms
     */
    public static final int ACTION_DOWN_DELAY = 100;
}
