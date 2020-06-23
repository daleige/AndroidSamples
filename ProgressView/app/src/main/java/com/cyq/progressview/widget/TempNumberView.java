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

    private NumberView mHour1View;
    private NumberView mHour2View;
    private NumberView mMinute1View;
    private NumberView mMinute2View;
    private NumberView mSecond1View;
    private NumberView mSecond2View;

    private TextView mColon1View;
    private TextView mColon2View;

    private LinearLayout mTempContainer, mClockContainer;

    public TempNumberView(Context context) {
        this(context, null);
    }

    public TempNumberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_progress_temp_number_layout, this, true);
        mSingleView = findViewById(R.id.mSingleNumber);
        mTenView = findViewById(R.id.mTenNumber);
        mHundredView = findViewById(R.id.mHundredNumber);
        mHour1View = findViewById(R.id.mHour1);
        mHour2View = findViewById(R.id.mHour2);
        mMinute1View = findViewById(R.id.mMinute1);
        mMinute2View = findViewById(R.id.mMinute2);
        mSecond1View = findViewById(R.id.mSecond1);
        mSecond2View = findViewById(R.id.mSecond2);
        mColon1View = findViewById(R.id.mColon1);
        mColon2View = findViewById(R.id.mColon2);
        mTempContainer = findViewById(R.id.mTempNumberContainer);
        mClockContainer = findViewById(R.id.mClockContainer);
        testCount();
    }

    /**
     * 全局变量
     */
    Disposable disposable;

    void testCount() {
        //设置为显示温度数字的模式
        mTempContainer.setVisibility(VISIBLE);
        mClockContainer.setVisibility(GONE);

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
                        setTemperature(500-Integer.parseInt(String.valueOf(count)));

                        //TODO 测试计时器
                        //setClock(3610 - Integer.parseInt(String.valueOf(count)));
                    }
                });
    }

    /**
     * 设置温度
     *
     * @param temperature
     */
    private void setTemperature(int temperature) {
        int mSingle = temperature % 10;
        int mTen = temperature / 10 % 10;
        int mHundred = temperature / 100 % 10;
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
        if (second > 60 - 1) {
            //大于1分钟
            mSecond1View.setVisibility(VISIBLE);
            mSecond2View.setVisibility(VISIBLE);
            mMinute1View.setVisibility(VISIBLE);
        } else if (second > 60 * 10 - 1) {
            //大于十分钟
            mMinute2View.setVisibility(VISIBLE);
        } else if (second > 60 * 60 - 1) {
            //大于60分钟
            mColon2View.setVisibility(VISIBLE);
            mHour1View.setVisibility(VISIBLE);
        } else if (second > 60 * 60 * 10 - 1) {
            //大于十个小时
            mHour2View.setVisibility(VISIBLE);
        }

        int hour = 0, min = 0, sec = 0;
        //计算出小时
        hour = (int) (second / (60 * 60));
        // 计算出分钟
        min = (int) ((second - hour * 3600) / 60);
        // 计算出秒数
        sec = (int) (second - hour * 3600 - min * 60);
        System.out.println();

        int mHour1Value = hour / 10 % 10;
        int mHour2Value = hour % 10;
        int mMinute1Value = min / 10 % 10;
        int mMinute2Value = min % 10;
        int mSecond1Value = sec / 10 % 10;
        int mSecond2Value = sec % 10;
        Log.e("test", hour + " : " + min + ":" + sec);
        Log.e("test", mHour1Value + " : " + mHour2Value + ":" + mMinute1Value + " : " + mMinute2Value + " : " + mSecond1Value + ":" + mSecond2Value);

        mSecond1View.setCurrentValue(mSecond1Value, second);
        mSecond2View.setCurrentValue(mSecond2Value, second);
        mMinute2View.setCurrentValue(mMinute2Value, second);

        if (mHour1Value > 0) {
            mHour1View.setVisibility(VISIBLE);
            mHour2View.setVisibility(VISIBLE);
            mMinute1View.setVisibility(VISIBLE);
            mColon1View.setVisibility(VISIBLE);
            mHour1View.setCurrentValue(mHour1Value, second);
            mHour2View.setCurrentValue(mHour2Value, second);
            mMinute1View.setCurrentValue(mMinute1Value, second);
        } else if (mHour2Value > 0) {
            mHour1View.setVisibility(GONE);
            mHour2View.setVisibility(VISIBLE);
            mMinute1View.setVisibility(VISIBLE);
            mColon1View.setVisibility(VISIBLE);
            mHour2View.setCurrentValue(mHour2Value, second);
            mMinute1View.setCurrentValue(mMinute1Value, second);
        } else if (mMinute1Value > 0) {
            mHour1View.setVisibility(GONE);
            mHour2View.setVisibility(GONE);
            mMinute1View.setVisibility(VISIBLE);
            mColon1View.setVisibility(GONE);
            mMinute1View.setCurrentValue(mMinute1Value, second);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
