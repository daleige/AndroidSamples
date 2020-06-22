package com.cyq.progressview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cyq.progressview.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author : ChenYangQi
 * date   : 2020/6/20 15:41
 * desc   : 温度变化的数字滚动控件
 */
public class TempNumberView extends LinearLayout {
    private NumberView mSingleView;
    private NumberView mTenView;
    private NumberView mHundredView;

    private NumberView mHour1;
    private NumberView mHour2;
    private NumberView mMinute1;
    private NumberView mMinute2;
    private NumberView mSecond1;
    private NumberView mSecond2;

    private TextView mColon1;
    private TextView mColon2;

    private int mSingle;
    private int mTen;
    private int mHundred;

    private int mHour1Value;
    private int mHour2Value;
    private int mMinute1Value;
    private int mMinute2Value;
    private int mSecond1Value;
    private int mSecond2Value;

    public TempNumberView(Context context) {
        this(context, null);
    }

    public TempNumberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.temp_number_layout, this, true);
        mSingleView = findViewById(R.id.mSingleNumber);
        mTenView = findViewById(R.id.mTenNumber);
        mHundredView = findViewById(R.id.mHundredNumber);
        mHour1 = findViewById(R.id.mHour1);
        mHour2 = findViewById(R.id.mHour2);
        mMinute1 = findViewById(R.id.mMinute1);
        mMinute2 = findViewById(R.id.mMinute2);
        mSecond1 = findViewById(R.id.mSecond1);
        mSecond2 = findViewById(R.id.mSecond2);
        mColon1 = findViewById(R.id.mColon1);
        mColon2 = findViewById(R.id.mColon2);

        testCount();
    }

    /**
     * 全局变量
     */
    Disposable disposable;

    void testCount() {
        disposable = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong + 1;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long count) throws Exception {
                        //TODO  测试 设置温度 1秒变化一次
                        //setTemperature(Integer.parseInt(String.valueOf(count)));

                        //TODO 测试计时器
                        setClock(3610 - Integer.parseInt(String.valueOf(count)));
                    }
                });
    }

    /**
     * 设置温度
     *
     * @param temperature
     */
    private void setTemperature(int temperature) {
        mSingle = temperature % 10;
        mTen = temperature / 10 % 10;
        mHundred = temperature / 100 % 10;
        Log.e("test", "个 十 百：" + mSingle + "---" + mTen + "---" + mHundred);
        mSingleView.setCurrentValue(mSingle, temperature);
        if (temperature > 9) {
            mTenView.setCurrentValue(mTen, temperature);
        }
        if (temperature > 99) {
            mHundredView.setCurrentValue(mHundred, temperature);
        }
    }


    /**
     * 设置时间
     */
    void setClock(int second) {
        Log.e("test", "时间-------------------：" + second);
        //假如倒计时的时间为200s
        if (second > 60 - 1) {
            //大于1分钟
            mSecond1.setVisibility(VISIBLE);
            mSecond2.setVisibility(VISIBLE);
            mMinute1.setVisibility(VISIBLE);
        } else if (second > 60 * 10 - 1) {
            //大于十分钟
            mMinute2.setVisibility(VISIBLE);
        } else if (second > 60 * 60 - 1) {
            //大于60分钟
            mColon2.setVisibility(VISIBLE);
            mHour1.setVisibility(VISIBLE);
        } else if (second > 60 * 60 * 10 - 1) {
            //大于十个小时
            mHour2.setVisibility(VISIBLE);
        }

        int hour = 0, min = 0, sec = 0;
        //计算出小时
        hour = (int) (second / (60 * 60));
        // 计算出分钟
        min = (int) ((second - hour * 3600) / 60);
        // 计算出秒数
        sec = (int) (second - hour * 3600 - min * 60);
        System.out.println();

        Log.e("test", hour + " : " + min + ":" + sec);
        mHour1Value = hour / 10 % 10;
        mHour2Value = hour % 10;
        mMinute1Value = min / 10 % 10;
        mMinute2Value = min % 10;
        mSecond1Value = sec / 10 % 10;
        mSecond2Value = sec % 10;
        Log.e("test", hour + " : " + min + ":" + sec + " : " + hour + " : " + min + ":" + sec);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
