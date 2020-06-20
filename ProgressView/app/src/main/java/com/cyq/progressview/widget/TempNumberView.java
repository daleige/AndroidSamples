package com.cyq.progressview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

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
    private int mSingle;
    private int mTen;
    private int mHundred;

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

        testCount();
    }

    private void setTemperature(int temperature) {
        mSingle = temperature % 10;
        mTen = temperature / 10 % 10;
        mHundred = temperature / 100 % 10;
        Log.e("test", "个 十 百：" + mSingle + "---" + mTen + "---" + mHundred);
        mSingleView.setCurrentValue(mSingle);
    }

    //   全局变量
    Disposable disposable;

    void testCount() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
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
                        Log.e("test", "count: " + count);
                        setTemperature(Integer.parseInt(String.valueOf(count)));
                    }
                });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
