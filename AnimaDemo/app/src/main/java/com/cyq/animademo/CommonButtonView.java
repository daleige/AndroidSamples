package com.cyq.animademo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2020/2/24 11:12
 * desc   :
 */
public class CommonButtonView extends LinearLayout implements View.OnTouchListener {

    private Context mContext;
    private int lightBtnWidth;
    private int lightBtnHeight;
    private int normalBtnWidth;
    private int normalBtnHeight;
    private int marginHeight = dip2px(5);

    private int widthColor = Color.parseColor("#FFFFFF");
    private int normalColor = Color.parseColor("#2A2A2A");
    private int lightColor;

    private int yellowColor = Color.parseColor("#FF5000");

    private int greenColor = Color.parseColor("#44A902");
    private int greePOressColor = Color.parseColor("#80C14A");
    private int blueColor = Color.parseColor("#2C8AFF");
    private int lightTextSize = 22;
    private int normalTextsize = 20;

    private String[] mItems;
    private List<View> views = new ArrayList<>();
    private CommonButtonViewAction mActionListener;
    private float roundRadius = dip2px(4);
//    private GradientDrawable lightDrawable;
//    private GradientDrawable normalDrawable;

    public CommonButtonView(Context context) {
        this(context, null);
    }

    public CommonButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //设置垂直方向排列
        setOrientation(LinearLayout.VERTICAL);
        mContext = getContext();
        lightBtnWidth = dip2px(226);
        lightBtnHeight = dip2px(50);
        normalBtnWidth = dip2px(213);
        normalBtnHeight = dip2px(46);
        //内容水平居中排列
        setGravity(Gravity.CENTER_HORIZONTAL);
        //动态创建shape
//        lightDrawable = new GradientDrawable();
//        normalDrawable = new GradientDrawable();
//        lightDrawable.setCornerRadius(roundRadius);
//        normalDrawable.setCornerRadius(roundRadius);
//        normalDrawable.setColor(normalColor);
    }

    /**
     * 设置按钮组件高亮颜色
     *
     * @param buttonType {@link ButtonType}
     */
    public void create(int buttonType, String[] items, boolean confirm,
                       CommonButtonViewAction commonButtonViewAction) {
        this.mActionListener = commonButtonViewAction;
        if (buttonType == ButtonType.YELLOW) {
            lightColor = yellowColor;
        } else if (buttonType == ButtonType.BLUE) {
            lightColor = blueColor;
        } else if (buttonType == ButtonType.GREEN) {
            lightColor = greenColor;
        } else {
            lightColor = normalColor;
        }

        if (items.length <= 0) {
            return;
        }
        mItems = items;
        for (int i = 0; i < mItems.length; i++) {
            if (lightColor != normalColor) {
                //组件有高亮颜色
                if (i == 0) {
                    TextView lightBtn = createLightBtn(mItems[i], buttonType);
                    views.add(lightBtn);
                } else {
                    TextView normalBtn = createNormalBtn(mItems[i]);
                    views.add(normalBtn);
                }
            } else {
                //组件无高亮颜色
                TextView normalBtn = createNormalBtn(mItems[i]);
                views.add(normalBtn);
            }
        }

        if (confirm) {
            //有确认按钮
            FrameLayout confirmBtn = createConfirmBtn(true);
            views.add(confirmBtn);
        } else {
            //有徐晓按钮
            FrameLayout confirmBtn = createConfirmBtn(false);
            views.add(confirmBtn);
        }

        //加载到View容器中
        for (int i = 0; i < views.size(); i++) {
            addView(views.get(i));
            final int finalI = i;
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mActionListener != null) {
                        mActionListener.onClick(finalI);
                    }
                }
            });

            views.get(i).setOnTouchListener(this);
        }
    }

    public void addAction(boolean confirm) {

    }

    /**
     * 创建高亮按钮
     *
     * @return
     */
    private TextView createLightBtn(String text, int colorType) {
        TextView lightBtn = new TextView(mContext);
        MarginLayoutParams lightLp = new MarginLayoutParams(lightBtnWidth, lightBtnHeight);
        lightLp.topMargin = dip2px(7);
        lightLp.leftMargin = dip2px(7);
        lightLp.rightMargin = dip2px(7);
        lightBtn.setLayoutParams(lightLp);
        if (colorType == ButtonType.YELLOW) {
            lightBtn.setBackgroundResource(R.drawable.yellow_common_button_bg);
        } else if (colorType == ButtonType.BLUE) {
            lightBtn.setBackgroundResource(R.drawable.blue_common_button_bg);
        } else if (colorType == ButtonType.GREEN) {
            lightBtn.setBackgroundResource(R.drawable.green_common_button_bg);
        }
        lightBtn.setTextSize(lightTextSize);
        lightBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        lightBtn.setTextColor(widthColor);
        lightBtn.setText(text);
        lightBtn.setGravity(Gravity.CENTER);
        return lightBtn;
    }

    /**
     * 创建普通按钮
     *
     * @return
     */
    private TextView createNormalBtn(String text) {
        TextView normalBtn = new TextView(mContext);
        MarginLayoutParams normalLp = new MarginLayoutParams(normalBtnWidth, normalBtnHeight);
        normalLp.topMargin = marginHeight;
        normalBtn.setLayoutParams(normalLp);
        normalBtn.setBackgroundResource(R.drawable.normal_common_button_bg);
        normalBtn.setTextSize(normalTextsize);
        normalBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        normalBtn.setTextColor(widthColor);
        normalBtn.setText(text);
        normalBtn.setGravity(Gravity.CENTER);
        return normalBtn;
    }

    /**
     * 创建确认取消按钮按钮
     *
     * @return
     */
    private FrameLayout createConfirmBtn(boolean b) {
        FrameLayout confirmBtn = new FrameLayout(mContext);
        MarginLayoutParams confirmLp = new MarginLayoutParams(normalBtnWidth, normalBtnHeight);
        confirmLp.topMargin = marginHeight;
        confirmLp.bottomMargin = dip2px(7);
        confirmBtn.setLayoutParams(confirmLp);
        ImageView iv = new ImageView(mContext);
        FrameLayout.LayoutParams ivLp = new FrameLayout.LayoutParams(dip2px(30), dip2px(30));
        ivLp.gravity = Gravity.CENTER;
        iv.setLayoutParams(ivLp);
        if (b) {
            iv.setBackgroundResource(R.drawable.set_icon_sure);
        } else {
            iv.setBackgroundResource(R.drawable.set_icon_delete);
        }
        confirmBtn.setBackgroundResource(R.drawable.normal_common_button_bg);
        confirmBtn.addView(iv);
        return confirmBtn;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 按下时执行放大动画
                ScaleAnimation bigScaleAnim = new ScaleAnimation(1f, 1.05f, 1f, 1.05f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                bigScaleAnim.setDuration(200);
                bigScaleAnim.setFillAfter(true);
                v.startAnimation(bigScaleAnim);
                break;
            case MotionEvent.ACTION_UP:
                // 抬起时执行复原动画
                ScaleAnimation minScaleAnim = new ScaleAnimation(1.05f, 1f, 1.05f, 1f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                minScaleAnim.setDuration(200);
                minScaleAnim.setFillAfter(true);
                v.startAnimation(minScaleAnim);
                break;
            default:
        }
        return false;
    }

    /**
     * 点击item的回调
     */
    public interface CommonButtonViewAction {
        void onClick(int position);
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
