package com.cyq.expendtextview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 8:26
 * desc   :
 */
public class ExpandTextView extends TextView implements View.OnClickListener {

    private static final int MAX_COLLAPSED_LINES = 8;// The default number of lines 默认显示行数为8行
    private static final int DEFAULT_ANIM_DURATION = 300; // The default animation duration 默认动画时长为300ms
    private static final float DEFAULT_ANIM_ALPHA_START = 0.7f;// The default alpha value when the animation starts

    private int mMaxCollapsedLines = 8;//最大显示行数
    private int mAnimationDuration;
    private float mAnimAlphaStart;
    private Drawable mExpandDrawable;//展开前显示图片
    private Drawable mCollapseDrawable;//展开后图片

    private int mCollapsedHeight;
    private int mTextHeightWithMaxLines;

    private boolean mCollapsed = true; // Show short version as default.标示现在所处的折叠状态
    private boolean mAnimating = false;
    private boolean needCollapse = true; //标示是否需要折叠已显示末尾的图标


    private int mDrawableSize = 0;


    /**
     * 表示箭头对齐方式,靠左/上,右/下,还是居中
     */
    private static final int ALIGN_RIGHT_BOTTOM = 0;
    private static final int ALIGN_LEFT_TOP = 1;
    private static final int ALIGN_CENTER = 2;
    private int arrowAlign = ALIGN_RIGHT_BOTTOM;

    /**
     * 表示箭头显示位置,在文字右边还是在文字下边
     */
    private static final int POSITION_RIGHT = 0;
    private static final int POSITION_BELOW = 1;
    private int arrowPosition = POSITION_RIGHT;

    /**
     * 箭头图标和文字的距离
     */
    private int arrowDrawablePadding = 0;

    /* Listener for callback */
    private OnExpandStateChangeListener mListener;


    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView, defStyleAttr, 0);
//        mMaxCollapsedLines = typedArray.getInt(R.styleable.ExpandTextView_maxCollapsedLines, MAX_COLLAPSED_LINES);
//        mAnimationDuration = typedArray.getInt(R.styleable.ExpandTextView_animDuration, DEFAULT_ANIM_DURATION);
//        mAnimAlphaStart = typedArray.getFloat(R.styleable.ExpandTextView_animAlphaStart, DEFAULT_ANIM_ALPHA_START);
//        mExpandDrawable = typedArray.getDrawable(R.styleable.ExpandTextView_expandDrawable);
//        mCollapseDrawable = typedArray.getDrawable(R.styleable.ExpandTextView_collapseDrawable);
//        arrowAlign = typedArray.getInteger(R.styleable.ExpandTextView_arrowAlign, ALIGN_RIGHT_BOTTOM);
//        arrowPosition = typedArray.getInteger(R.styleable.ExpandTextView_arrowPosition, POSITION_RIGHT);
//        arrowDrawablePadding = (int) typedArray.getDimension(R.styleable.ExpandTextView_arrowPadding, DensityUtil.dp2px(context, 2f));

//        typedArray.recycle();

        if (mExpandDrawable == null) {
//            mExpandDrawable = getDrawable(getContext(), R.drawable.ic_expand_small_holo_light);
        }
        if (mCollapseDrawable == null) {
//            mCollapseDrawable = getDrawable(getContext(), R.drawable.ic_collapse_small_holo_light);
        }

        setClickable(true);
        setOnClickListener(this);
    }

    private boolean isDrawablePaddingResolved = false;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getVisibility() == GONE || mAnimating) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        //重置高度重新测量
        //设置为wrap_content，重新measure
        getLayoutParams().height = -2;
        setMaxLines(Integer.MAX_VALUE);
        //测量TextView总高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLineCount() <= mMaxCollapsedLines) {
            needCollapse = false;
            return;
        }

        needCollapse = true;

        mTextHeightWithMaxLines = getRealTextViewHeight(this);
        if (mCollapsed) {
            setMaxLines(mMaxCollapsedLines);
        }

        mDrawableSize = mExpandDrawable.getIntrinsicWidth();
        if (!isDrawablePaddingResolved) {
            if (arrowPosition == POSITION_RIGHT) {
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight() + mDrawableSize + arrowDrawablePadding, getPaddingBottom());
            } else {
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + mExpandDrawable.getIntrinsicHeight() + arrowDrawablePadding);
            }
            isDrawablePaddingResolved = true;
        }

        //设置完成后重新测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mCollapsed) {
            mCollapsedHeight = getMeasuredHeight();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (needCollapse) {
            int left, top;
            if (arrowPosition == POSITION_RIGHT) {
                left = getWidth() - getTotalPaddingRight() + arrowDrawablePadding;
                switch (arrowAlign) {
                    case ALIGN_LEFT_TOP:
                        top = getTotalPaddingTop();
                        break;
                    case ALIGN_CENTER:
                        top = (getHeight() - mExpandDrawable.getIntrinsicHeight()) / 2;
                        break;
                    case ALIGN_RIGHT_BOTTOM:
                    default:
                        top = getHeight() - getTotalPaddingBottom() - mExpandDrawable.getIntrinsicHeight();
                        break;
                }
            } else {
                top = getHeight() - getTotalPaddingBottom() + arrowDrawablePadding;
                switch (arrowAlign) {
                    case ALIGN_LEFT_TOP:
                        left = getTotalPaddingLeft();
                        break;
                    case ALIGN_CENTER:
                        left = (getWidth() - mExpandDrawable.getIntrinsicWidth()) / 2;
                        break;
                    case ALIGN_RIGHT_BOTTOM:
                    default:
                        left = getWidth() - getTotalPaddingRight() - mExpandDrawable.getIntrinsicWidth();
                        break;
                }
            }
            canvas.translate(left, top);
            if (mCollapsed) {
                mExpandDrawable.setBounds(0, 0, mExpandDrawable.getIntrinsicWidth(), mExpandDrawable.getIntrinsicHeight());
                mExpandDrawable.draw(canvas);
            } else {
                mCollapseDrawable.setBounds(0, 0, mCollapseDrawable.getIntrinsicWidth(), mCollapseDrawable.getIntrinsicHeight());
                mCollapseDrawable.draw(canvas);
            }
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        setCollapsed(true);
        super.setText(text, type);
    }

    @Override
    public void onClick(View v) {
        if (!needCollapse) {
            return;//行数不足,不响应点击事件
        }
        mCollapsed = !mCollapsed;

        Bitmap collapseBM = Bitmap.createBitmap(mCollapseDrawable.getIntrinsicWidth(), mCollapseDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas cv2 = new Canvas(collapseBM);
        mCollapseDrawable.setBounds(0, 0, mCollapseDrawable.getIntrinsicWidth(), mCollapseDrawable.getIntrinsicHeight());
        mCollapseDrawable.draw(cv2);

        ImageSpan isExpand = new ImageSpan(mExpandDrawable);
        ImageSpan isCollapse = new ImageSpan(getContext(), collapseBM);

        SpannableString spannableString = new SpannableString("icon");
        spannableString.setSpan(mCollapsed ? isExpand : isCollapse, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // mark that the animation is in progress
        mAnimating = true;

        Animation animation;
        if (mCollapsed) {
            animation = new ExpandCollapseAnimation(this, getHeight(), mCollapsedHeight);
        } else {
            animation = new ExpandCollapseAnimation(this, getHeight(), mTextHeightWithMaxLines);
        }

        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (mListener != null) {
                    mListener.onChangeStateStart(!mCollapsed);
                }
                applyAlphaAnimation(ExpandTextView.this, mAnimAlphaStart);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // clear animation here to avoid repeated applyTransformation() calls
                clearAnimation();
                // clear the animation flag
                mAnimating = false;

                // notify the listener
                if (mListener != null) {
                    mListener.onExpandStateChanged(ExpandTextView.this, !mCollapsed);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        clearAnimation();
        startAnimation(animation);
    }

    private class ExpandCollapseAnimation extends Animation {
        private final View mTargetView;
        private final int mStartHeight;
        private final int mEndHeight;

        public ExpandCollapseAnimation(View view, int startHeight, int endHeight) {
            mTargetView = view;
            mStartHeight = startHeight;
            mEndHeight = endHeight;
            setDuration(mAnimationDuration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final int newHeight = (int) ((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight);
            mTargetView.getLayoutParams().height = newHeight;
            setMaxHeight(newHeight);
            if (Float.compare(mAnimAlphaStart, 1.0f) != 0) {
                applyAlphaAnimation(ExpandTextView.this, mAnimAlphaStart + interpolatedTime * (1.0f - mAnimAlphaStart));
            }
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    private Drawable getDrawable(Context context, int drawableResId) {
        Resources resources = context.getResources();
        if (isPostLolipop()) {
            return resources.getDrawable(drawableResId, context.getTheme());
        } else {
            return resources.getDrawable(drawableResId);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void applyAlphaAnimation(View view, float alpha) {
        if (isPostHoneycomb()) {
            view.setAlpha(alpha);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
            // make it instant
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
    }

    private boolean isPostHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    private boolean isPostLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private int getRealTextViewHeight(TextView textView) {
        int textHeight = textView.getLayout().getLineTop(textView.getLineCount());
        int padding = textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom();
        return textHeight + padding;
    }


    public void setCollapsed(boolean isCollapsed) {
        mCollapsed = isCollapsed;
    }


    public interface OnExpandStateChangeListener {
        void onChangeStateStart(boolean willExpand);
        /**
         * Called when the expand/collapse animation has been finished
         *
         * @param textView   - TextView being expanded/collapsed
         * @param isExpanded - true if the TextView has been expanded
         */
        void onExpandStateChanged(TextView textView, boolean isExpanded);
    }

    public void setOnExpandStateChangeListener(OnExpandStateChangeListener listener) {
        mListener = listener;
    }
}
