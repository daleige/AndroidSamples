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
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2019-12-07 21:44
 * Author: ChenYangQi
 * Description:面包刻度选择器
 */
public class BreadScaleView extends ScrollView {
    private List<ScaleBean> mData = new ArrayList<>();
    private LinearLayout container;
    private Context mContext;
    private int itemHeight = 300;//item默认高度
    private int textSize = 40;//刻度文字默认大小
    private int displayCount = 9;//默认展示9个item

    public BreadScaleView(Context context) {
        this(context, null);
    }

    public BreadScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreadScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        for (int i = 0; i < 14; i++) {
            ScaleBean bean = new ScaleBean();
            bean.setScale(String.valueOf(i + 1));
            bean.setType(ScaleType.MIDDLE);
            mData.add(bean);
        }

        mContext = getContext();
        container = new LinearLayout(mContext);
        container.setOrientation(LinearLayout.VERTICAL);
        for (ScaleBean bean : mData) {
            container.addView(createItemView(bean.getScale(), bean.getType()));
        }
        this.addView(container);
    }

    /**
     * 创建Item
     *
     * @param scaleValue 刻度上要展示的文字
     * @param empty      展示左侧面包的图标类型
     * @return 返回item的View
     */
    private View createItemView(String scaleValue, ScaleType empty) {
        LinearLayout itemView = new LinearLayout(mContext);
        itemView.setOrientation(LinearLayout.HORIZONTAL);
        itemView.setGravity(Gravity.CENTER_VERTICAL);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                itemHeight));

        TextView tv = new TextView(mContext);
        tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                itemHeight));
        tv.setSingleLine(true);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tv.setText(scaleValue);
        tv.setGravity(Gravity.CENTER);

        ImageView iv = new ImageView(mContext);
        iv.setLayoutParams(new LayoutParams(80, 80));
        iv.setBackgroundColor(Color.parseColor("#f44336"));

        View lineView = new View(mContext);
        lineView.setLayoutParams(new LayoutParams(200, 20));
        lineView.setBackgroundColor(Color.parseColor("#f44336"));

        ImageView ivTriangle = new ImageView(mContext);
        ivTriangle.setLayoutParams(new LayoutParams(40, 40));
        ivTriangle.setBackgroundColor(Color.parseColor("#f44336"));

        itemView.addView(iv);
        itemView.addView(lineView);
        itemView.addView(ivTriangle);
        itemView.addView(tv);
        return itemView;
    }
}
