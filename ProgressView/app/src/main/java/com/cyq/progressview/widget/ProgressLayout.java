package com.cyq.progressview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;


/**
 * @author : ChenYangQi
 * date   : 2020/5/23 13:41
 * desc   : 自定义组合布局 底部的扇形粒子动画+数字切换
 */
public class ProgressLayout extends FrameLayout {
    private MySmartProgressView mMySmartProgressView;
    private FrameLayout mNumberContainer;

    public ProgressLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_progress_progress_view_layout, this, true);
    }

    /**
     * 设置当前显示温度
     *
     * @param temperature 当前温度
     * @param targetTemperature 目标温度
     */
    public void setTemperature(int temperature, int targetTemperature) {
        if (temperature < 0) {
            return;
        }
        mMySmartProgressView.setCurrentTemperature(temperature);
    }
}
