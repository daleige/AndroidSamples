package com.cyq.progressview.button;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.cyq.progressview.R;
import com.cyq.progressview.widget.Utils;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * @author : ChenYangQi
 * date   : 2020/6/3 10:06
 * desc   : 全局的按钮控件，以下三类按钮的功能集合
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MyButton extends FrameLayout implements View.OnTouchListener, A6ButtonInterface {

    private int mBtnType;
    private int mColorType;
    private int mActionType;
    private String text;

    private GradientDrawable drawable;
    private int normalColor;
    private int pressedColor;
    private int touchColor;
    private int touchColorResource;
    private ScaleAnimation bigScaleAnim;
    private ValueAnimator colorAnimator;
    private boolean mEnable;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MyButton);
        mBtnType = array.getInt(R.styleable.MyButton_a6btn_type, ButtonBean.LIGHT_BUTTON);
        mColorType = array.getInt(R.styleable.MyButton_a6btn_color, -1);
        //如果设置了按钮颜色，则设置为按钮为高亮按钮
        if (mColorType > 0) {
            mBtnType = ButtonBean.LIGHT_BUTTON;
        } else {
            mColorType = ButtonBean.YELLOW;
        }
        mActionType = array.getInt(R.styleable.MyButton_a6btn_action, ButtonBean.POSITIVE);
        mEnable = array.getBoolean(R.styleable.MyButton_a6btn_enable, true);
        text = array.getString(R.styleable.MyButton_a6btn_text);
        array.recycle();
        init();
    }

    private void init() {
        removeAllViews();
        switch (mBtnType) {
            case ButtonBean.LIGHT_BUTTON:
                initLightButton();
                break;
            case ButtonBean.NORMAL_BUTTON:
                initNormalButton();
                break;
            case ButtonBean.ACTION_BUTTON:
                initActionButton();
                break;
            default:
        }
    }

    /**
     * 限定通用按钮组件的尺寸大小
     * 初始化margin,高亮按钮的margin为4px,普通按钮margin为9px
     * 最终的结果为普通按钮之间的margin为18px,普通按钮和高亮按钮之间的margin为13px
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        if (mBtnType == ButtonBean.LIGHT_BUTTON) {
            mlp.leftMargin = mlp.topMargin = mlp.rightMargin = mlp.bottomMargin = 4;
        } else {
            mlp.leftMargin = mlp.topMargin = mlp.rightMargin = mlp.bottomMargin = 9;
        }
        setLayoutParams(mlp);

        int mWidth;
        int mHeight;
        if (mBtnType == ButtonBean.LIGHT_BUTTON) {
            mWidth = dip2px(340);
            mHeight = dip2px(75);
        } else {
            mWidth = dip2px(318);
            mHeight = dip2px(70);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 初始化普通类型的按钮
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initNormalButton() {
        setBackgroundResource(R.drawable.normal_normal);

        TextView mTv = new TextView(getContext());
        FrameLayout.LayoutParams ivLp = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ivLp.gravity = Gravity.CENTER;
        mTv.setLayoutParams(ivLp);
        mTv.setGravity(Gravity.CENTER);
        mTv.setTextColor(Color.WHITE);
        mTv.setTextAppearance(R.style.text_menu);
        if (!TextUtils.isEmpty(text)) {
            mTv.setText(text);
        }

        drawable = new GradientDrawable();
        drawable.setCornerRadius(Utils.dip2px(6, getContext()));
        drawable.setStroke(1, getResources().getColor(R.color.btn_stork_color));
        normalColor = getResources().getColor(R.color.normal_bt_color);
        touchColor = getResources().getColor(R.color.normal_bt_press_color);
        pressedColor = getResources().getColor(R.color.normal_bt_trigger_color);
        drawable.setColor(normalColor);

        addView(mTv);
        initAnim();
        setOnTouchListener(this);
    }

    /**
     * 初始化高亮类型的按钮
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initLightButton() {
        drawable = new GradientDrawable();
        drawable.setCornerRadius(dip2px(6));
        if (mColorType == ButtonBean.YELLOW) {
            normalColor = getResources().getColor(R.color.main_bt_color);
            pressedColor = getResources().getColor(R.color.main_bt_trigger_color);
            touchColor = getResources().getColor(R.color.main_bt_press_color);
            touchColorResource = R.drawable.yellow_pressed;
        } else if (mColorType == ButtonBean.GREEN) {
            normalColor = getResources().getColor(R.color.clean_bt_color);
            pressedColor = getResources().getColor(R.color.clean_bt_trigger_color);
            touchColor = getResources().getColor(R.color.clean_bt_press_color);
            touchColorResource = R.drawable.green_pressed;
        } else if (mColorType == ButtonBean.BLUE) {
            normalColor = getResources().getColor(R.color.setting_bt_color);
            pressedColor = getResources().getColor(R.color.setting_bt_trigger_color);
            touchColor = getResources().getColor(R.color.setting_bt_press_color);
            touchColorResource = R.drawable.blue_pressed;
        }
        drawable.setColor(normalColor);
        drawable.setStroke(1, getResources().getColor(R.color.btn_stork_color));
        setBackgroundDrawable(drawable);

        TextView mTv = new TextView(getContext());
        FrameLayout.LayoutParams ivLp = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ivLp.gravity = Gravity.CENTER;
        mTv.setLayoutParams(ivLp);
        mTv.setGravity(Gravity.CENTER);
        mTv.setTextColor(Color.WHITE);
        mTv.setTextAppearance(R.style.text_menu_light);

        if (!TextUtils.isEmpty(text)) {
            mTv.setText(text);
        }
        addView(mTv);

        initAnim();
        setOnTouchListener(this);
    }

    /**
     * 初始化图标类型按钮
     */
    private void initActionButton() {
        ImageView mImgIcon = new ImageView(getContext());
        FrameLayout.LayoutParams ivLp = new FrameLayout.LayoutParams(dip2px(60), dip2px(60));
        ivLp.gravity = Gravity.CENTER;
        mImgIcon.setLayoutParams(ivLp);
        if (mActionType == ButtonBean.POSITIVE) {
            mImgIcon.setBackgroundResource(R.drawable.common_btn_icon_determine);
        } else if (mActionType == ButtonBean.NEGATIVE) {
            mImgIcon.setBackgroundResource(R.drawable.common_btn_icon_cancel);
        }
        drawable = new GradientDrawable();
        drawable.setCornerRadius(dip2px(6));
        drawable.setStroke(1, getResources().getColor(R.color.btn_stork_color));
        normalColor = getResources().getColor(R.color.normal_bt_color);
        touchColor = getResources().getColor(R.color.normal_bt_press_color);
        pressedColor = getResources().getColor(R.color.normal_bt_trigger_color);
        drawable.setColor(normalColor);
        setBackgroundDrawable(drawable);
        addView(mImgIcon);
        initAnim();
        setOnTouchListener(this);
    }

    private void initAnim() {
        bigScaleAnim = MyAnimUtils.getClickScaleAnim();
        colorAnimator = MyAnimUtils.getClickColorAnim(touchColor, pressedColor);
        colorAnimator.addUpdateListener(animation -> {
            int color = (int) animation.getAnimatedValue();
            drawable.setColor(color);
            setBackgroundDrawable(drawable);
        });
        colorAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                MultiTouchManager.disableAllClick = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画执行结束，因为点击事件被消费，此时模拟点击事件触发Onclick
                performClick();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mBtnType == ButtonBean.LIGHT_BUTTON) {
                            if (mColorType == ButtonBean.YELLOW) {
                                setBackgroundResource(R.drawable.yellow_normal);
                            } else if (mColorType == ButtonBean.GREEN) {
                                setBackgroundResource(R.drawable.green_normal);
                            } else if (mColorType == ButtonBean.BLUE) {
                                setBackgroundResource(R.drawable.blue_normal);
                            }
                        } else {
                            setBackgroundResource(R.drawable.normal_normal);
                        }
                        MultiTouchManager.disableAllClick = false;
                    }
                }, 100);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                MultiTouchManager.disableAllClick = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!mEnable) {
            //按钮禁用状态，不处理事件，转交给上层处理
            return false;
        }

        if (MultiTouchManager.disableAllClick) {
            return true;
        }
        int width = getWidth();
        int height = getHeight();
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mBtnType == ButtonBean.LIGHT_BUTTON) {
                    setBackgroundResource(touchColorResource);
                } else {
                    setBackgroundResource(R.drawable.normal_pressed);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (x < 0 || x > width || y < 0 || y > height) {
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    return onTouch(v, event);
                } else if (!colorAnimator.isRunning()) {
                    //动画未结束前不能点击
                    v.startAnimation(bigScaleAnim);
                    colorAnimator.start();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (x < 0 || x > width || y < 0 || y > height) {
                    //滑出控件外
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    return onTouch(v, event);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mBtnType == ButtonBean.LIGHT_BUTTON) {
                    if (mColorType == ButtonBean.YELLOW) {
                        setBackgroundResource(R.drawable.yellow_normal);
                    } else if (mColorType == ButtonBean.GREEN) {
                        setBackgroundResource(R.drawable.green_normal);
                    } else if (mColorType == ButtonBean.BLUE) {
                        setBackgroundResource(R.drawable.blue_normal);
                    }
                } else {
                    setBackgroundResource(R.drawable.normal_normal);
                }
                break;
            default:
        }
        //消费点击事件
        return true;
    }

    @Override
    public void setVisibility(int visibility) {
        clearAnimation();
        super.setVisibility(visibility);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void setColorType(ButtonType colorType) {
        if (colorType == ButtonType.YELLOW) {
            mColorType = ButtonBean.YELLOW;
        } else if (colorType == ButtonType.GREEN) {
            mColorType = ButtonBean.GREEN;
        } else if (colorType == ButtonType.BLUE) {
            mColorType = ButtonBean.BLUE;
        }
        //重新执行onMeasure onLayout
        requestLayout();
        init();
    }

    @Override
    public void setBtnType(ButtonType btnType) {
        if (btnType == ButtonType.LIGHT) {
            mBtnType = ButtonBean.LIGHT_BUTTON;
        } else if (btnType == ButtonType.NORMAL) {
            mBtnType = ButtonBean.NORMAL_BUTTON;
        } else if (btnType == ButtonType.ACTION) {
            mBtnType = ButtonBean.ACTION_BUTTON;
        }
        requestLayout();
        init();
    }

    @Override
    public void setActionType(ButtonType actionType) {
        if (actionType == ButtonType.POSITIVE) {
            mActionType = ButtonBean.POSITIVE;
        } else if (actionType == ButtonType.NEGATIVE) {
            mActionType = ButtonBean.NEGATIVE;
        }
        requestLayout();
        init();
    }

    /**
     * 设置是否可以触发点击事件和动效
     *
     * @param enable
     */
    @Override
    public void setMyTouchEnable(boolean enable) {
        mEnable = enable;
    }

    public String getText() {
        return text;
    }

    /**
     * 设置文本
     *
     * @param str
     */
    public void setText(String str) {
        if (!TextUtils.isEmpty(str)) {
            text = str;
        }
        requestLayout();
        init();
    }
}
