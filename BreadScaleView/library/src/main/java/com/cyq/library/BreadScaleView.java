package com.cyq.library;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author : ChenYangQi
 * date   : 2019/12/9 9:08
 * desc   :
 */
public class BreadScaleView extends FrameLayout {
    private BreadScrollView mBreadScrollView;
    private ImageView ivCursor;
    private View lineView;
    private View fristLineView;
    private View lastLineView;
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
     * 中间横线选中状态宽度
     */
    private int lineHeightWidth = 240;
    /**
     * 中间横线的选中状态高度
     */
    private int lineHeightHeight = 24;

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
    private int lightColor = Color.parseColor("#616161");
    /**
     * 二级刻度颜色
     */
    private int middleColor = Color.parseColor("#fafafa");
    /**
     * 三级刻度颜色
     */
    private int heightColor = Color.parseColor("#ff6d00");

    /**
     * 三角形游标的宽高
     */
    private int cursorWidth = 60;

    public BreadScaleView(@NonNull Context context) {
        this(context, null);
    }

    public BreadScaleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreadScaleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 控件初始化
     */
    private void init() {
        mContext = getContext();
        //滑动布局属性
        mBreadScrollView = new BreadScrollView(getContext());
        mBreadScrollView.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //三角形游标布局属性
        ivCursor = new ImageView(mContext);
        int marginLeft = ivWidth + lineMarginLeft + lineHeightWidth;
        int marginTop = (displayCount / 2) * itemHeight + ((itemHeight - cursorWidth) / 2);
        MarginLayoutParams ivLayoutParams = new MarginLayoutParams(cursorWidth, cursorWidth);
        ivLayoutParams.leftMargin = marginLeft;
        ivLayoutParams.topMargin = marginTop;
        ivCursor.setLayoutParams(ivLayoutParams);
        ivCursor.setBackgroundResource(R.drawable.bread_icon_pointer);

        //中间黄色横线
        lineView = new View(mContext);
        MarginLayoutParams layoutParams = new MarginLayoutParams(lineHeightWidth, lineHeightHeight);
        layoutParams.leftMargin = ivWidth + lineMarginLeft;
        layoutParams.topMargin = (displayCount / 2) * itemHeight + ((itemHeight - lineHeightHeight) / 2);
        lineView.setLayoutParams(layoutParams);
        lineView.setBackgroundColor(heightColor);

        //第一根白线刻度
        fristLineView = new View(mContext);
        MarginLayoutParams fristLp = new MarginLayoutParams(lineWidth, lineHeight);
        fristLp.leftMargin = ivWidth + lineMarginLeft + (lineHeightWidth - lineWidth) / 2;
        fristLp.topMargin = (displayCount / 2 - 1) * itemHeight + ((itemHeight - lineHeight) / 2);
        fristLineView.setLayoutParams(fristLp);
        fristLineView.setBackgroundColor(middleColor);

        lastLineView = new View(mContext);
        MarginLayoutParams lastLp = new MarginLayoutParams(lineWidth, lineHeight);
        lastLp.leftMargin = ivWidth + lineMarginLeft + (lineHeightWidth - lineWidth) / 2;
        lastLp.topMargin = (displayCount / 2 + 1) * itemHeight + ((itemHeight - lineHeight) / 2);
        lastLineView.setLayoutParams(lastLp);
        lastLineView.setBackgroundColor(middleColor);


        addView(mBreadScrollView);
        addView(ivCursor);
        addView(lineView);
        addView(fristLineView);
        addView(lastLineView);
    }

    public void moveTo(int position){
        mBreadScrollView.moveToPosition(position);
    }
}
