package com.cyq.library;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.telecom.Call;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2019-12-07 21:44
 *
 * @Author: ChenYangQi
 * Description:烤面包刻度选择器部分
 */
public class BreadScrollView extends ObservableScrollView implements ObservableScrollView.OnScrollListener {
    private List<ScaleBean> mData = new ArrayList<>();
    private LinearLayout container;
    private Context mContext;
    /**
     * item默认高度
     */
    private int itemHeight = 160;
    /**
     * 默认展示9个item
     */
    private int displayCount = 9;
    /**
     * 刻度文字默认大小，单位：sp
     */
    private int tvSize = 24;
    /**
     * 面包图片宽高
     */
    private int ivWidth = 140;
    /**
     * 中间横线宽度
     */
    private int lineWidth = 200;
    /**
     * 中间横线的高度
     */
    private int lineHeight = 12;

    /**
     * 中间横线选中状态宽度
     */
    private int lineHeightWidth = 240;
    /**
     * 中间横线的选中状态高度
     */
    private int lineHeightHeight = 24;

    /**
     * 刻度文字marginLeft
     */
    private int lineMarginLeft = 100;
    /**
     * 面包图标marginRight
     */
    private int lineMarginRight = 100;
    /**
     * 未选中刻度颜色
     */
    private int lightColor = Color.parseColor("#222222");
    /**
     * 二级刻度颜色
     */
    private int middleColor = Color.parseColor("#D8D8D8");
    /**
     * 二级文字颜色
     */
    private int middleTxtColor = Color.parseColor("#BDBDBD");
    /**
     * 三级刻度颜色
     */
    private int heightColor = Color.parseColor("#DD5F00");

    /**
     * 标记Y方向已滑动距离
     */
    int scrollY = 0;
    /**
     * 用来标记是否是自动滚动
     */
    boolean autoScrollTag = false;


    public BreadScrollView(Context context) {
        this(context, null);
    }

    public BreadScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreadScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 控件初始化
     */
    private void init() {
        this.setVerticalScrollBarEnabled(false);
        for (int i = 1; i <= 20; i++) {
            ScaleBean bean = new ScaleBean();
            if (i >= 4) {
                bean.setScale(String.valueOf(i));
                bean.setType(ScaleType.MIDDLE);
            } else {
                bean.setScale(String.valueOf(i));
                bean.setType(ScaleType.LIGHT);
            }

            if (i == 1) {
                bean.setBreadType(BreadType.LIGHT);
            } else if (i == 5) {
                bean.setBreadType(BreadType.MIDDLE);
            } else if (i == 9) {
                bean.setBreadType(BreadType.HEIGHT);
            } else {
                bean.setBreadType(BreadType.EMPTY);
            }
            mData.add(bean);
        }

        mContext = getContext();
        container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < mData.size(); i++) {
            container.addView(createItemView(mData.get(i), i));
        }
        this.addView(container);
        setOnScrollListener(this);
    }

    MarginLayoutParams tvLayoutParams;

    /**
     * 创建Item
     *
     * @param bean 刻度信息
     * @return 返回item的View
     */
    private View createItemView(ScaleBean bean, int index) {
        LinearLayout itemView = new LinearLayout(mContext);
        itemView.setOrientation(LinearLayout.HORIZONTAL);
        itemView.setGravity(Gravity.CENTER_VERTICAL);
        ViewGroup.LayoutParams itemLayoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, itemHeight);
        itemView.setLayoutParams(itemLayoutParams);

        //创建刻度文字
        TextView tv = new TextView(mContext);
        tvLayoutParams =
                new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
        tv.setLayoutParams(tvLayoutParams);
        tv.setSingleLine(true);
        if (bean.getType().equals(ScaleType.HEIGHT)) {
            tv.setTextColor(heightColor);
        } else if (bean.getType().equals(ScaleType.MIDDLE)) {
            tv.setTextColor(middleTxtColor);
        } else {
            tv.setTextColor(lightColor);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, tvSize);
        tv.setText(bean.getScale());
        tv.setId(index);
        tv.setGravity(Gravity.CENTER);

        //创建面包图片
        ImageView iv = new ImageView(mContext);
        MarginLayoutParams ivLayoutParams = new MarginLayoutParams(ivWidth, ivWidth);
        iv.setLayoutParams(ivLayoutParams);
        if (bean.getBreadType().equals(BreadType.HEIGHT)) {
            iv.setBackgroundResource(R.drawable.bread_icon_3);
        } else if (bean.getBreadType().equals(BreadType.MIDDLE)) {
            iv.setBackgroundResource(R.drawable.bread_icon_2);
        } else if (bean.getBreadType().equals(BreadType.LIGHT)) {
            iv.setBackgroundResource(R.drawable.bread_icon_1);
        }
        //创建中间的横线刻度
        View lineView = new View(mContext);
        MarginLayoutParams layoutParams = new MarginLayoutParams(lineWidth, lineHeight);
        lineView.setLayoutParams(layoutParams);
        layoutParams.leftMargin = lineMarginLeft + (lineHeightWidth - lineWidth) / 2;
        layoutParams.rightMargin = lineMarginRight + (lineHeightWidth - lineWidth) / 2;
        lineView.setBackgroundColor(lightColor);
        itemView.addView(iv);
        itemView.addView(lineView);
        itemView.addView(tv);
        return itemView;
    }

    /**
     * 修改ScrollView的滑动速度
     *
     * @param velocityY
     */
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 4);
    }

    /**
     * 设置控件高度为=item高度*item个数
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, itemHeight * displayCount);
    }

    /**
     * 滑动状态监听
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(ObservableScrollView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                //停止滑动
                if (!autoScrollTag) {
                    return;
                }
                int topCount = scrollY / itemHeight;
                int criticalY = scrollY % itemHeight;
                if (criticalY > itemHeight / 2) {
                    //滑动到下一个位置
                    currentPosition = topCount + 1;
                    BreadScrollView.this.smoothScrollTo(0, currentPosition * itemHeight);
                } else {
                    //滑动到上一个位置
                    currentPosition = topCount;
                    BreadScrollView.this.smoothScrollTo(0, currentPosition * itemHeight);
                }
                autoScrollTag = false;
                break;
            //按下
            case SCROLL_STATE_TOUCH_SCROLL:
                autoScrollTag = true;
                break;
            //开始滑动
            case SCROLL_STATE_FLING:
                break;
            default:
        }
    }

    /**
     * 记录当前滑动到的位置
     */
    int currentPosition;

    @Override
    public void onScroll(ObservableScrollView view, boolean isTouchScroll, int x, int y, int oldx
            , int oldY) {
        scrollY = y;
        int count = y / (itemHeight - 3);
        int position = count + displayCount / 2;
        int childSize = mData.size();
        for (int i = 0; i < childSize; i++) {
            if (position == i) {
                Log.i("test", "位置：" + position);
                ((TextView) container.findViewById(position)).setTextColor(heightColor);
            } else {
                ((TextView) container.findViewById(i)).setTextColor(middleColor);
            }
        }
    }
}
