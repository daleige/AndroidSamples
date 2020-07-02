package com.cyq.progressview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;
import com.yuyashuai.frameanimation.FrameAnimationView;


/**
 * @author : ChenYangQi
 * date   : 2020/5/23 13:41
 * desc   : 自定义组合布局 底部的扇形粒子动画+数字切换
 */
public class ProgressLayout extends FrameLayout {
    private MySmartProgressView mMySmartProgressView;
    private TempNumberView mTempNumberView;
    public FrameAnimationView mWaveBgView;

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
        mMySmartProgressView = findViewById(R.id.mMySmartProgressView);
        mTempNumberView = findViewById(R.id.mTempNumberView);
        mWaveBgView = findViewById(R.id.mWaveBgView);
    }

    /**
     * 设置当前显示温度
     *
     * @param temperature       当前温度
     * @param targetTemperature 目标温度
     */
    public void setTemperature(float temperature, float targetTemperature) {
        mMySmartProgressView.setCurrentTemperature(temperature, targetTemperature);
    }
}
