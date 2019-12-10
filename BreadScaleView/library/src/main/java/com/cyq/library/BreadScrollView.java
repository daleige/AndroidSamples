package com.cyq.library;

import android.content.Context;
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
    private int itemHeight;
    /**
     * 默认展示9个item
     */
    private int displayCount;
    /**
     * 刻度文字默认大小，单位：sp
     */
    private int tvSize;
    /**
     * 面包图片宽高
     */
    private int ivWidth;
    /**
     * 中间横线宽度
     */
    private int lineWidth;
    /**
     * 中间横线的高度
     */
    private int lineHeight;

    /**
     * 中间横线选中状态宽度
     */
    private int lineHeightWidth;

    /**
     * 刻度文字marginLeft
     */
    private int lineMarginLeft;
    /**
     * 面包图标marginRight
     */
    private int lineMarginRight;
    /**
     * 未选中刻度颜色
     */
    private int lightColor;
    /**
     * 二级刻度颜色
     */
    private int middleColor;
    /**
     * 二级文字颜色
     */
    private int middleTxtColor;
    /**
     * 三级刻度颜色
     */
    private int heightColor;


    /**
     * 标记Y方向已滑动距离
     */
    int scrollY = 0;
    /**
     * 用来标记是否是自动滚动
     */
    boolean autoScrollTag = false;

    int itemCount;
    int ofSetHeight;

    public BreadScrollView(Context context, int itemHeight, int displayCount, int tvSize, int ivWidth,
                           int lineWidth, int lineHeight, int lineHeightWidth, int lineMarginLeft, int lineMarginRight,
                           int lightColor, int middleColor, int middleTxtColor, int heightColor) {
        super(context);
        this.itemHeight = itemHeight;
        this.displayCount = displayCount;
        this.tvSize = tvSize;
        this.ivWidth = ivWidth;
        this.lineWidth = lineWidth;
        this.lineHeight = lineHeight;
        this.lineHeightWidth = lineHeightWidth;
        this.lineMarginLeft = lineMarginLeft;
        this.lineMarginRight = lineMarginRight;
        this.lightColor = lightColor;
        this.middleColor = middleColor;
        this.middleTxtColor = middleTxtColor;
        this.heightColor = heightColor;
    }

    /**
     * 控件初始化
     */
    private void init() {
        this.setVerticalScrollBarEnabled(false);

        //如果没有数据就展示默认的9个
        if (mData.size() <= 0) {
            for (int i = 0; i < 9; i++) {
                ScaleBean bean = new ScaleBean();
                bean.setScale(String.valueOf(i + 1));
                if (i == 0) {
                    //下标位置0时，设置面包片图片1
                    bean.setBreadType(BreadType.LIGHT);
                } else if (i == 4) {
                    //下标位置4时，设置面包片图片2
                    bean.setBreadType(BreadType.MIDDLE);
                } else if (i == 8) {
                    //下标位置8时，设熟面包片图片3
                    bean.setBreadType(BreadType.HEIGHT);
                } else {
                    bean.setBreadType(BreadType.EMPTY);
                }
                mData.add(bean);
            }
        }

        mContext = getContext();
        itemCount = mData.size();
        ofSetHeight = displayCount / 2 * itemHeight;
        container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.VERTICAL);

        //添加顶部占位空控件
        View topView = new View(mContext);
        topView.setLayoutParams(new ViewGroup.LayoutParams(1, ofSetHeight));
        View bottomView = new View(mContext);
        bottomView.setLayoutParams(new ViewGroup.LayoutParams(1, ofSetHeight));
        container.addView(topView);
        for (int i = 0; i < itemCount; i++) {
            container.addView(createItemView(mData.get(i), i));
        }
        container.addView(bottomView);
        this.addView(container);
        setOnScrollListener(this);
        moveToPosition(4);
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
                new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, itemHeight);
        tv.setLayoutParams(tvLayoutParams);
        tv.setSingleLine(true);
        tv.setTextColor(lightColor);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
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
     * 设置数据
     *
     * @param datas
     */
    public void setData(List<ScaleBean> datas) {
        mData = datas;
        init();
    }

    /**
     * 移动指定位置
     *
     * @param index
     */
    public void moveToPosition(final int index) {
        if (index < 0 || index > (itemCount - 1)) {
            return;
        }
        BreadScrollView.this.post(new Runnable() {
            @Override
            public void run() {
                BreadScrollView.this.scrollTo(0, ofSetHeight + (index - displayCount / 2) * itemHeight);
            }
        });
    }

    /**
     * 修改ScrollView的滑动速度
     *
     * @param velocityY
     */
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
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
                int currentY = scrollY % itemHeight;
                if (currentY >= itemHeight / 2) {
                    //滑动到下一个位置
                    currentPosition = topCount + 1;
                    BreadScrollView.this.smoothScrollTo(0, currentPosition * itemHeight);
                } else {
                    //滑动到上一个位置
                    currentPosition = topCount;
                    BreadScrollView.this.smoothScrollTo(0, currentPosition * itemHeight);
                }
                autoScrollTag = false;
                if (onItemChangeListener != null) {
                    onItemChangeListener.onItemChanged(currentPosition);
                }
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

    /**
     * 标记上一次的position，减少position相同时重复setTextColor
     */
    int oldPosition = -100;

    @Override
    public void onScroll(ObservableScrollView view, boolean isTouchScroll, int x, int y, int oldx
            , int oldY) {
        scrollY = y;
        //控制文字变色
        int modular = (y - ofSetHeight) % itemHeight;
        int position = (y - ofSetHeight) / itemHeight + displayCount / 2;
        if (modular >= itemHeight / 2) {
            position++;
        }
        if (oldPosition == position) {
            return;
        }

        if (oldPosition >= 0 && onItemChangeListener != null) {
            onItemChangeListener.onItemChange(position, oldPosition);
        }

        for (int i = 0; i < itemCount; i++) {
            if (position == i) {
                ((TextView) container.findViewById(position)).setTextColor(heightColor);
            } else {
                if (position - 1 >= 0 && i == position - 1) {
                    ((TextView) container.findViewById(i)).setTextColor(middleTxtColor);

                } else if (position + 1 < itemCount && i == position + 1) {
                    ((TextView) container.findViewById(i)).setTextColor(middleTxtColor);
                } else {
                    ((TextView) container.findViewById(i)).setTextColor(lightColor);
                }
            }

            if (position == itemCount - 1) {
                onFristOrLastItem.lastItem(false);
            } else {
                onFristOrLastItem.lastItem(true);
            }
            if (position == 0) {
                onFristOrLastItem.firstItem(false);
            } else {
                onFristOrLastItem.firstItem(true);
            }
        }
        oldPosition = position;
    }


    public void setOnFristOrLastItem(OnFirstOrLastItem onFirstOrLastItem) {
        this.onFristOrLastItem = onFirstOrLastItem;
    }

    private OnFirstOrLastItem onFristOrLastItem;

    public interface OnFirstOrLastItem {
        void firstItem(boolean isVisible);

        void lastItem(boolean isVisible);
    }


    public void setOnItemChangeListener(OnItemChangeListener onItemChangeListener) {
        this.onItemChangeListener = onItemChangeListener;
    }

    private OnItemChangeListener onItemChangeListener;

    public interface OnItemChangeListener {
        void onItemChanged(int position);

        void onItemChange(int position, int oldPosition);
    }
}
