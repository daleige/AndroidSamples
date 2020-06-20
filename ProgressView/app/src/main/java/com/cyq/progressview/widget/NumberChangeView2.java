package com.cyq.progressview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.progressview.R;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author : ChenYangQi
 * date   : 2020/6/20 10:19
 * desc   :
 */
public class NumberChangeView2 extends FrameLayout {
    private RecyclerView mRecyclerview;
    private NumberAdapter mAdapter;
    Disposable disposable;  //   全局变量

    public NumberChangeView2(@NonNull Context context) {
        this(context, null);
    }

    public NumberChangeView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberChangeView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_number_container, this, true);
        mRecyclerview = findViewById(R.id.mRecyclerView);
        mAdapter = new NumberAdapter(getContext());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.setAdapter(mAdapter);
        testCount();
    }

    void testCount() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong + 1;
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long count) throws Exception {
                        Log.d("test", "count: " + count);
                        mRecyclerview.smoothScrollToPosition((int) (count % 10));
                    }
                });
    }

    /**
     * 禁止外部触摸滑动
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
