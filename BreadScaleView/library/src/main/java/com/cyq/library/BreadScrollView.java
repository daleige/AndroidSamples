package com.cyq.library;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2019-12-07 21:44
 * Author: ChenYangQi
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
        for (ScaleBean bean : mData) {
            container.addView(createItemView(bean));
        }
        this.addView(container);
        setOnScrollListener(this);
    }

    /**
     * 创建Item
     *
     * @param bean 刻度信息
     * @return 返回item的View
     */
    private View createItemView(ScaleBean bean) {
        LinearLayout itemView = new LinearLayout(mContext);
        itemView.setOrientation(LinearLayout.HORIZONTAL);
        itemView.setGravity(Gravity.CENTER_VERTICAL);
        ViewGroup.LayoutParams itemLayoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, itemHeight);
        itemView.setLayoutParams(itemLayoutParams);

        //创建刻度文字
        TextView tv = new TextView(mContext);
        MarginLayoutParams tvLayoutParams =
                new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
        tv.setLayoutParams(tvLayoutParams);
        tv.setSingleLine(true);
        //TODO 需要根据不同的状态变更颜色
        if (bean.getType().equals(ScaleType.HEIGHT)) {
            tv.setTextColor(heightColor);
        } else if (bean.getType().equals(ScaleType.MIDDLE)) {
            tv.setTextColor(middleTxtColor);
        } else {
            tv.setTextColor(lightColor);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, tvSize);
        tv.setText(bean.getScale());
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
        } else {
            //不需要设置面包图标
        }
        //创建中间的横线刻度
        View lineView = new View(mContext);
        MarginLayoutParams layoutParams = new MarginLayoutParams(lineWidth, lineHeight);
        lineView.setLayoutParams(layoutParams);
//        if (bean.getType().equals(ScaleType.HEIGHT)) {
//            lineView.setBackgroundColor(heightColor);
//            //重新设置选中状态的刻度的宽高
//            layoutParams.width = lineHeightWidth;
//            layoutParams.height = lineHeightHeight;
//            //设置选中刻度的margin
//            layoutParams.leftMargin = lineMarginLeft;
//            layoutParams.rightMargin = lineMarginRight;
//        } else
        if (bean.getType().equals(ScaleType.MIDDLE)) {
            lineView.setBackgroundColor(middleColor);
            //设置非选中刻度的margin
            layoutParams.leftMargin = lineMarginLeft + (lineHeightWidth - lineWidth) / 2;
            layoutParams.rightMargin = lineMarginRight + (lineHeightWidth - lineWidth) / 2;
        } else {
            lineView.setBackgroundColor(lightColor);
            //设置非选中刻度的margin
            layoutParams.leftMargin = lineMarginLeft + (lineHeightWidth - lineWidth) / 2;
            layoutParams.rightMargin = lineMarginRight + (lineHeightWidth - lineWidth) / 2;
        }
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
        super.fling(velocityY / 2);
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

                //记录当前滑动到的位置
                int currentPosition;
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
                //刷新UI，更新到选中的位置为currentItemPosition
                //refreshView(currentPosition+displayCount/2);
                break;
            //按下
            case SCROLL_STATE_TOUCH_SCROLL:
                autoScrollTag = true;
                break;
            //开始滑动
            case SCROLL_STATE_FLING:
                autoScrollTag = true;
                break;
            default:
        }
    }

    @Override
    public void onScroll(ObservableScrollView view, boolean isTouchScroll, int x, int y, int oldx
            , int oldy) {
        scrollY = y;
    }

    /**
     * 更新UI到当前选中的位置
     *
     * @param currentPosition
     */
    private void refreshView(int currentPosition) {
        Log.i("test", "当前滑动到的位置：" + currentPosition);
        for (int i = 0; i < mData.size(); i++) {
            if (i == currentPosition) {
                mData.get(i).setType(ScaleType.HEIGHT);
            } else if (i == currentPosition - 1) {
                mData.get(i).setType(ScaleType.MIDDLE);
            } else if (i == currentPosition + 1) {
                mData.get(i).setType(ScaleType.MIDDLE);
            } else {
                mData.get(i).setType(ScaleType.LIGHT);
            }
        }
        container.removeAllViews();
        for (ScaleBean bean : mData) {
            container.addView(createItemView(bean));
        }
    }
}
