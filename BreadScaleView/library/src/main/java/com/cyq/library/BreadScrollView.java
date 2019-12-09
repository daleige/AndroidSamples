package com.cyq.library;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
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
            if (i == 4 || i == 6) {
                bean.setScale(String.valueOf(i));
                bean.setType(ScaleType.MIDDLE);
            } else if (i == 5) {
                bean.setScale(String.valueOf(i));
                bean.setType(ScaleType.HEIGHT);
            } else {
                bean.setScale(String.valueOf(i));
                bean.setType(ScaleType.LIGHT);
            }
            mData.add(bean);
        }

        mContext = getContext();
        container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.VERTICAL);
        for (ScaleBean bean : mData) {
            container.addView(createItemView(bean.getScale(), bean.getType()));
        }
        this.addView(container);
        setOnScrollListener(this);
    }

    /**
     * 创建Item
     *
     * @param scaleValue 刻度上要展示的文字
     * @param type       展示左侧面包的图标类型
     * @return 返回item的View
     */
    private View createItemView(String scaleValue, ScaleType type) {
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
        if (type.equals(ScaleType.HEIGHT)) {
            tv.setTextColor(heightColor);
        } else if (type.equals(ScaleType.MIDDLE)) {
            tv.setTextColor(middleTxtColor);
        } else {
            tv.setTextColor(lightColor);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, tvSize);
        tv.setText(scaleValue);
        tv.setGravity(Gravity.CENTER);

        //创建面包图片
        ImageView iv = new ImageView(mContext);
        MarginLayoutParams ivLayoutParams = new MarginLayoutParams(ivWidth, ivWidth);
        iv.setLayoutParams(ivLayoutParams);
        //TODO 替换成面包图标
        iv.setBackgroundResource(R.drawable.bread_icon_3);

        //创建中间的横线刻度
        View lineView = new View(mContext);
        MarginLayoutParams layoutParams = new MarginLayoutParams(lineWidth, lineHeight);
        layoutParams.leftMargin = lineMarginLeft;
        layoutParams.rightMargin = lineMarginRight;
        lineView.setLayoutParams(layoutParams);
        //TODO 需要根据不同的状态变更颜色
        if (type.equals(ScaleType.HEIGHT)) {
            lineView.setBackgroundColor(heightColor);
        } else if (type.equals(ScaleType.MIDDLE)) {
            lineView.setBackgroundColor(middleColor);
        } else {
            lineView.setBackgroundColor(lightColor);
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
                if (criticalY > itemHeight / 2) {
                    //滑动到下一个位置
                    BreadScrollView.this.smoothScrollTo(0, (topCount + 1) * itemHeight);
                } else {
                    //滑动到上一个位置
                    BreadScrollView.this.smoothScrollTo(0, topCount * itemHeight);
                }
                autoScrollTag = false;
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
    public void onScroll(ObservableScrollView view, boolean isTouchScroll, int x, int y, int oldX
            , int oldY) {
        scrollY = y;
    }
}
