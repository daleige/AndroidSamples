package com.cyq.customview.nineLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : ChenYangQi
 * date   : 2020/1/15 14:59
 * desc   : 九宫格图片控件
 */
public class NineImageLayout extends ViewGroup {
    /**
     * 控件宽度
     */
    private int width = 800;
    /**
     * 图片之间间隔的大小
     */
    private int itemMargin = 10;
    /**
     * 单个图片的宽度和高度
     */
    private int itemWidth;
    /**
     * 一张图片允许的最大宽高范围
     */
    private int singleImageWidth = 600;
    private NineImageAdapter mAdapter;

    public NineImageLayout(Context context) {
        this(context, null);
    }

    public NineImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //一张图片的宽高
        itemWidth = (width - 2 * itemMargin) / 3;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewHeight = 0;
        int viewWidth = 0;
        int count = getChildCount();
        if (count == 1) {
            //TODO 单独处理
            setMeasuredDimension(singleViewWidth, singleViewHeight);
            return;
        } else if (count <= 3) {
            viewHeight = itemWidth;
            if (count == 2) {
                viewWidth = 2 * itemWidth + itemMargin;
            } else if (count == 3) {
                viewWidth = width;
            }
        } else if (count <= 6) {
            viewHeight = 2 * itemWidth + itemMargin;
            if (count == 4) {
                viewWidth = 2 * itemWidth + itemMargin;
            } else {
                viewWidth = width;
            }
        } else if (count <= 9) {
            viewHeight = width;
            viewWidth = width;
        }
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            switch (i) {
                case 0:
                    if (count == 1) {
                        //TODO 单独处理
                        left = 0;
                        top = 0;
                        right = left + singleViewWidth;
                        bottom = top + singleViewHeight;
                    } else {
                        left = 0;
                        top = 0;
                        right = left + itemWidth;
                        bottom = top + itemWidth;
                    }
                    break;
                case 1:
                    left = itemWidth + itemMargin;
                    top = 0;
                    right = left + itemWidth;
                    bottom = top + itemWidth;
                    break;
                case 2:
                    left = itemWidth * 2 + itemMargin * 2;
                    top = 0;
                    right = width;
                    bottom = top + itemWidth;
                    break;
                case 3:
                    left = 0;
                    top = itemWidth + itemMargin;
                    right = left + itemWidth;
                    bottom = top + itemWidth;
                    break;
                case 4:
                    left = itemWidth + itemMargin;
                    top = itemWidth + itemMargin;
                    right = left + itemWidth;
                    bottom = top + itemWidth;
                    break;
                case 5:
                    left = (itemWidth + itemMargin) * 2;
                    top = itemWidth + itemMargin;
                    right = width;
                    bottom = top + itemWidth;
                    break;
                case 6:
                    left = 0;
                    top = (itemWidth + itemMargin) * 2;
                    right = left + itemWidth;
                    bottom = width;
                    break;
                case 7:
                    left = itemWidth + itemMargin;
                    top = (itemWidth + itemMargin) * 2;
                    right = left + itemWidth;
                    bottom = width;
                    break;
                case 8:
                    left = (itemWidth + itemMargin) * 2;
                    top = (itemWidth + itemMargin) * 2;
                    right = width;
                    bottom = width;
                    break;
                default:
                    break;
            }
            childView.layout(left, top, right, bottom);
        }
    }

    int singleViewWidth = 0;
    int singleViewHeight = 0;

    /**
     * 传入单张图片的宽高
     *
     * @param width
     * @param height
     */
    public void setSingleImage(int width, int height) {
        if (getChildCount() != 1) {
            return;
        }
        if (width >= height) {
            singleViewWidth = singleImageWidth;
            singleViewHeight = (int) (singleImageWidth * ((double) height / (double) width));
        } else {
            singleViewHeight = singleImageWidth;
            singleViewWidth = (int) (singleImageWidth * ((double) width / (double) height));
        }
        getChildAt(0).layout(0, 0, singleViewWidth, singleViewHeight);
        setMeasuredDimension(singleViewWidth, singleViewHeight);
    }

    /**
     * 设置数据源
     *
     * @param adapter
     */
    public void setAdapter(NineImageAdapter adapter) {
        mAdapter = adapter;
        removeAllViews();
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            final View view = mAdapter.createView(LayoutInflater.from(getContext()), this, i);
            mAdapter.bindView(view, i);
            addView(view);
            bindClickEvent(i, view);
        }
    }

    /**
     * 绑定点击事件
     *
     * @param position
     * @param view
     */
    private void bindClickEvent(final int position, final View view) {
        if (mAdapter == null) {
            return;
        }
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.OnItemClick(position, view);
            }
        });
    }

    /**
     * 获取MarginLayoutParams必须要重写这几个方法
     *
     * @return
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
