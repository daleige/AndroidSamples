package com.cyq.customview.flowLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : ChenYangQi
 * date   : 2020/1/15 9:39
 * desc   : 自定义流试布局
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        //记录行宽
        int lineWidth = 0;
        //记录行高
        int lineHeight = 0;
        //记录总宽度
        int width = 0;
        //记录总高度
        int height = 0;
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View chileView = getChildAt(i);
            measureChild(chileView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) chileView.getLayoutParams();

            int childWidth = chileView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = chileView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (lineWidth + childWidth > measureWidth - (getPaddingLeft() + getPaddingRight())) {
                //需要换行
                width = Math.max(lineWidth, width);
                height += +lineHeight;
                //因为当前行放不下，需要放到下一行，所以下一行的行高行宽为此时的ChildView的宽高
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不需要换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            //最后添加最后一行的宽高
            if (i == count - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        if (measureHeightMode == MeasureSpec.EXACTLY) {
            height = getMeasuredHeight();
        } else if (measureHeightMode == MeasureSpec.AT_MOST) {
            height = height + getPaddingTop() + getPaddingBottom();
        } else if (measureHeightMode == MeasureSpec.UNSPECIFIED) {
            height = height + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineWidth = 0;
        int lineHeight = 0;
        int top = getPaddingTop();
        int left = getPaddingLeft();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View chileView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) chileView.getLayoutParams();

            int childWidth = chileView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = chileView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (lineWidth + childWidth > getMeasuredWidth() - (getPaddingLeft() + getPaddingRight())) {
                //需要换行
                top += lineHeight;
                left = getPaddingLeft();
                //因为当前行放不下，需要放到下一行，所以下一行的行高行宽为此时的ChildView的宽高
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不需要换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            //计算childView的left  top  right  bottom
            int leftChild = left + lp.leftMargin;
            int topChild = top + lp.topMargin;
            int rightChild = leftChild + chileView.getMeasuredWidth();
            int bottomChild = topChild + chileView.getMeasuredHeight();
            chileView.layout(leftChild, topChild, rightChild, bottomChild);
            left += childWidth;
        }
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
