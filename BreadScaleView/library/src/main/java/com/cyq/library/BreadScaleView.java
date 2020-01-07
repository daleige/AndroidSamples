package com.cyq.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

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
    private int itemHeight = 50;
    /**
     * 默认展示9个item
     */
    private int displayCount = 9;
    /**
     * 刻度文字默认大小，单位：sp
     */
    private int tvSize = 25;
    /**
     * 面包图片宽高
     */
    private int ivWidth = 50;
    /**
     * 中间横线宽度
     */
    private int lineWidth = 45;
    /**
     * 中间横线选中状态宽度
     */
    private int lineHeightWidth = 50;
    /**
     * 中间横线的选中状态高度
     */
    private int lineHeightHeight = 4;

    /**
     * 中间横线的高度
     */
    private int lineHeight = 2;
    /**
     * 中间横线marginLeft
     */
    private int lineMarginLeft = 33;
    /**
     * 中间横线marginRight
     */
    private int lineMarginRight = 28;

    /**
     * 未选中刻度颜色
     */
    private int triangleLeftMargin = 2;

    /**
     * 1级刻度颜色
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
     * 三角形游标的宽高
     */
    private int cursorWidth = 60;

    public BreadScaleView(@NonNull Context context) {
        this(context, null);
    }

    public BreadScaleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreadScaleView(@NonNull Context context, @Nullable AttributeSet attrs,
                          int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BreadScaleView);
        itemHeight = array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_itemHeight,
                dip2px(itemHeight));
        displayCount = array.getInteger(R.styleable.BreadScaleView_bread_displayCount,
                displayCount);
        heightColor = array.getColor(R.styleable.BreadScaleView_bread_heightColor, heightColor);
        tvSize = array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_tvSize,
                dip2px(tvSize));
        ivWidth = array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_ivWidth,
                dip2px(ivWidth));
        lineWidth = array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_lineWidth,
                dip2px(lineWidth));
        lineHeight = array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_lineHeight,
                dip2px(lineHeight));
        lineHeightWidth =
                array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_lineHeightWidth,
                        dip2px(lineHeightWidth));
        lineHeightHeight =
                array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_lineHeightHeight,
                        dip2px(lineHeightHeight));
        lineMarginLeft =
                array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_lineMarginLeft,
                        dip2px(lineMarginLeft));
        lineMarginRight =
                array.getDimensionPixelSize(R.styleable.BreadScaleView_bread_lineMarginRight,
                        dip2px(lineMarginRight));
        lightColor = array.getColor(R.styleable.BreadScaleView_bread_lightColor, lightColor);
        middleColor = array.getColor(R.styleable.BreadScaleView_bread_middleColor, middleColor);
        middleTxtColor = array.getColor(R.styleable.BreadScaleView_bread_middleTxtColor,
                middleTxtColor);
        array.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = ivWidth + lineMarginLeft + lineHeightWidth + lineMarginRight + dip2px(30);
        int height = itemHeight * displayCount;
        super.onMeasure(width, height);
    }

    /**
     * 控件初始化
     */
    private void init() {
        mContext = getContext();
        //滑动布局属性
        mBreadScrollView = new BreadScrollView(getContext(), itemHeight, displayCount, tvSize,
                ivWidth,
                lineWidth, lineHeight, lineHeightWidth, lineMarginLeft, lineMarginRight,
                lightColor, middleColor, middleTxtColor, heightColor);
        mBreadScrollView.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mBreadScrollView.setOnFristOrLastItem(new BreadScrollView.OnFirstOrLastItem() {
            @Override
            public void firstItem(boolean isVisible) {
                if (lastLineView == null) {
                    return;
                }
                if (isVisible) {
                    fristLineView.setVisibility(VISIBLE);
                } else {
                    fristLineView.setVisibility(GONE);
                }
            }

            @Override
            public void lastItem(boolean isVisible) {
                if (lastLineView == null) {
                    return;
                }
                if (isVisible) {
                    lastLineView.setVisibility(VISIBLE);
                } else {
                    lastLineView.setVisibility(GONE);
                }
            }
        });

        //三角形游标布局属性
        ivCursor = new ImageView(mContext);
        int marginLeft = ivWidth + lineMarginLeft + lineHeightWidth + triangleLeftMargin;
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
        layoutParams.topMargin =
                (displayCount / 2) * itemHeight + ((itemHeight - lineHeightHeight) / 2);
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

    /**
     * 设置选择结果的回调
     *
     * @param listener
     */
    public void setOnItemChangeLietener(BreadScrollView.OnItemChangeListener listener) {
        if (mBreadScrollView != null && listener != null) {
            mBreadScrollView.setOnItemChangeListener(listener);
        }
    }

    /**
     * 设置数据源
     *
     * @param datas
     */
    public void setData(List<ScaleBean> datas) {
        if (mBreadScrollView != null && datas.size() > 0) {
            mBreadScrollView.setData(datas);
        }
    }

    /**
     * 滑动到下标具体位置
     *
     * @param index
     */
    public void scrollTo(int index) {
        if (mBreadScrollView != null && index >= 0) {
            mBreadScrollView.moveToPosition(index);
        }
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    LightingColorFilter filter = new LightingColorFilter(0xffff00, 0x000000);
    Paint paint = new Paint();
    int redColor = Color.parseColor("#DD5F00");

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        Rect rect = new Rect(0, 400, 1080, 700);
//        setLayerType(LAYER_TYPE_SOFTWARE, null);
        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.save();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawRect(rect, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layer);
    }
}
