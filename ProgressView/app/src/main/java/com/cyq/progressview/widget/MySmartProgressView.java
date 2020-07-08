package com.cyq.progressview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.cyq.progressview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 14:24
 * desc   : 温度进度控件
 */
public class MySmartProgressView extends View {
    /**
     * 控件宽高
     */
    private int width, height;
    /**
     * 粒子总个数
     */
    private int pointCount = 100;
    /**
     * 粒子列表
     */
    private List<AnimPoint> mPointList = new ArrayList<>(pointCount);
    /**
     * 粒子外层圆环原点坐标和半径长度
     */
    private int mCenterX, mCenterY;

    /**
     * 主圆环内径到外径的中点到圆心的半径
     */
    private int mRadius;
    /**
     * 粒子外层圆环的画笔
     */
    private Paint mOutCirclePaint;
    /**
     * 底色圆画笔
     */
    private Paint mBackShadePaint;
    /**
     * 粒子画笔
     */
    private Paint mPointPaint;
    /**
     * 底色圆环画笔
     */
    private Paint mBackCirclePaint;
    /**
     * 保温圆环的线性渐变色
     */
    private SweepGradient mSweepGradient;

    /**
     * 内环到外环的颜色变化数字
     */
    private int[] mRadialGradientColors = new int[6];
    private float[] mRadialGradientStops = {0F, 0.62F, 0.86F, 0.94F, 0.98F, 1F};
    private Paint mSweptPaint;
    private RadialGradient mRadialGradient;
    private Random mRandom = new Random();
    /**
     * 扇形粒子区域的路径，用于裁剪画布范围
     */
    private Path mArcPath;
    /**
     * 指针目标图层
     */
    private Bitmap mBitmapDST;
    /**
     * 指针源图层
     */
    private Bitmap mBitmapSRT;
    /**
     * 离屏绘制图层混合的指针区域
     */
    private RectF mPointerRectF;
    /**
     * 绘制指针的layoutId
     */
    private int mPointerLayoutId;
    /**
     * 指针的图层混合模式
     */
    private PorterDuffXfermode mXfermode;
    /**
     * 指针的颜色
     */
    private int mPointerColor;
    private Paint mBmpPaint;

    /**
     * 外层粒子圆环的边框大小
     */
    private int mOutCircleStrokeWidth;

    /**
     * 外阴影的宽度
     */
    private int outerShaderWidth;
    private float mCurrentAngle;
    /**
     * 是否是保温
     */
    private boolean isKeepWare = false;
    /**
     * 温度环的颜色属性
     */
    private int insideColor1 = getContext().getColor(R.color.progress_inside_color1);
    private int outsizeColor1 = getContext().getColor(R.color.progress_outsize_color1);
    private int progressColor1 = getContext().getColor(R.color.progress_progress_color1);
    private int pointColor1 = getContext().getColor(R.color.progress_point_color1);
    private int bgCircleColor1 = getContext().getColor(R.color.progress_bg_circle_color1);
    private int insideColor2 = getContext().getColor(R.color.progress_inside_color2);
    private int outsizeColor2 = getContext().getColor(R.color.progress_outsize_color2);
    private int progressColor2 = getContext().getColor(R.color.progress_progress_color2);
    private int pointColor2 = getContext().getColor(R.color.progress_point_color2);
    private int bgCircleColor2 = getContext().getColor(R.color.progress_bg_circle_color2);
    private int insideColor3 = getContext().getColor(R.color.progress_inside_color3);
    private int outsizeColor3 = getContext().getColor(R.color.progress_outsize_color3);
    private int progressColor3 = getContext().getColor(R.color.progress_progress_color3);
    private int pointColor3 = getContext().getColor(R.color.progress_point_color3);
    private int bgCircleColor3 = getContext().getColor(R.color.progress_bg_circle_color3);
    private int insideColor4 = getContext().getColor(R.color.progress_inside_color4);
    private int outsizeColor4 = getContext().getColor(R.color.progress_outsize_color4);
    private int progressColor4 = getContext().getColor(R.color.progress_progress_color4);
    private int pointColor4 = getContext().getColor(R.color.progress_point_color4);
    private int bgCircleColor4 = getContext().getColor(R.color.progress_bg_circle_color4);
    private int insideColor5 = getContext().getColor(R.color.progress_inside_color5);
    private int outsizeColor5 = getContext().getColor(R.color.progress_outsize_color5);
    private int progressColor5 = getContext().getColor(R.color.progress_progress_color5);
    private int pointColor5 = getContext().getColor(R.color.progress_point_color5);
    private int bgCircleColor5 = getContext().getColor(R.color.progress_bg_circle_color5);
    private ProgressParameter mParameter = new ProgressParameter();

    /**
     * 保温的进度圆变色值
     */
    private int mLinearGradientColor1 = getContext().getColor(R.color.progress_linearGradient_color1);
    private int mLinearGradientColor2 = getContext().getColor(R.color.progress_linearGradient_color2);
    /**
     * 上一次圆环的进度，按0~3600计数
     */
    private float lastTimeProgress = 0;

    /**
     * 当前圆环的进度
     */
    private float currentProgress = 0;

    /**
     * 跳到下一个圆环进度的动画
     */
    private ValueAnimator progressAnim;

    /**
     * 保温模式下进度条变色旋转动画
     */
    private ValueAnimator mOutCircleAnim;

    /**
     * 保温模式下圆环当前的角度
     */
    private float mOutCircleAnger;
    private ValueAnimator mPointsAnimator;
    private boolean isCleanMode = false;

    /**
     * 构造方法
     *
     * @param context
     * @param parentWidth       控件宽度
     * @param outerShaderWidth  外阴影的宽度
     * @param circleStrokeWidth 住圆框的宽度
     */
    public MySmartProgressView(Context context,
                               int parentWidth,
                               int parentHeight,
                               int outerShaderWidth,
                               int circleStrokeWidth) {
        this(context, parentWidth, outerShaderWidth, circleStrokeWidth);
    }


    public MySmartProgressView(Context context,
                               int parentWidth,
                               int outerShaderWidth,
                               int circleStrokeWidth) {
        this(context, null, parentWidth, outerShaderWidth, circleStrokeWidth);
    }

    public MySmartProgressView(Context context,
                               @Nullable AttributeSet attrs,
                               int parentWidth,
                               int outerShaderWidth,
                               int circleStrokeWidth) {
        this(context, attrs, 0, parentWidth, outerShaderWidth, circleStrokeWidth);
    }

    public MySmartProgressView(Context context,
                               @Nullable AttributeSet attrs,
                               int defStyleAttr,
                               int parentWidth,
                               int outerShaderWidth,
                               int circleStrokeWidth) {
        super(context, attrs, defStyleAttr);
        this.height = this.width = parentWidth;
        this.mOutCircleStrokeWidth = circleStrokeWidth;
        this.outerShaderWidth = outerShaderWidth;
        init();
    }

    private void init() {
        //初始化宽高颜色值等
        initView();
        //初始化画笔
        initPaint();
        //初始化指针Bitmap画布
        initBitmap();
        //初始化动画
        initAnim();
    }

    /**
     * 初始化控件的各类宽高，边框，半径等大小
     */
    private void initView() {
        mPointerColor = progressColor1;
        int transparentColor = Color.parseColor("#00000000");
        mRadialGradientColors[0] = transparentColor;
        mRadialGradientColors[1] = transparentColor;
        mRadialGradientColors[4] = transparentColor;
        mRadialGradientColors[5] = transparentColor;
        mCenterX = width / 2;
        mCenterY = height / 2;
        // 粒子圆环的宽度
        mRadius = mCenterX - outerShaderWidth - mOutCircleStrokeWidth / 2;
    }

    /**
     * 初始化各类画笔
     */
    private void initPaint() {
        //step3：内圈到外圈渐变色画笔
        mSweptPaint = new Paint();
        mSweptPaint.setAntiAlias(true);
        mSweptPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRadialGradient = new RadialGradient(
                0,
                0,
                mCenterX,
                mRadialGradientColors,
                mRadialGradientStops,
                Shader.TileMode.CLAMP);
        mSweptPaint.setShader(mRadialGradient);

        //step1：初始化外层圆环的画笔
        mOutCirclePaint = new Paint();
        mOutCirclePaint.setStyle(Paint.Style.STROKE);
        mOutCirclePaint.setAntiAlias(true);
        mOutCirclePaint.setStrokeWidth(mOutCircleStrokeWidth);
        int[] mLinearGradientColors = {
                mLinearGradientColor2,
                mLinearGradientColor2,
                mLinearGradientColor1,
                mLinearGradientColor1,
                mLinearGradientColor2,
                mLinearGradientColor2};
        float[] mLinearGradientPositions = {0F, 0.05F, 0.12F, 0.88F, 0.95F, 1F};
        mSweepGradient = new SweepGradient(
                0F,
                0F,
                mLinearGradientColors,
                mLinearGradientPositions);

        //step2：初始化运动粒子的画笔
        mPointPaint = new Paint();
        //mPointPaint.setStyle(Paint.Style.FILL);
        //TODO 设备端羽化效果CPU不支持，暂不设置
        mPointPaint.setMaskFilter(new BlurMaskFilter(4, BlurMaskFilter.Blur.NORMAL));

        //初始化底色圆画笔
        mBackCirclePaint = new Paint();
        mBackCirclePaint.setAntiAlias(true);
        mBackCirclePaint.setStrokeWidth(mOutCircleStrokeWidth);
        mBackCirclePaint.setStyle(Paint.Style.STROKE);

        //初始化底色圆得initAnimator画笔
        mBackShadePaint = new Paint();
        mBackShadePaint.setAntiAlias(true);
        mBackShadePaint.setColor(bgCircleColor1);

        //指针画笔颜色
        mBmpPaint = new Paint();
    }

    /**
     * 初始化指针图片的Bitmap
     */
    private void initBitmap() {
        float f = 130F / 656F;
        mBitmapDST = BitmapFactory.decodeResource(getResources(), R.drawable.indicator);
        float mBitmapDSTHeight = width * f;
        float mBitmapDSTWidth = mBitmapDSTHeight * mBitmapDST.getWidth() / mBitmapDST.getHeight();
        //初始化指针的图层混合模式
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        mPointerRectF = new RectF(0, 0, mBitmapDSTWidth, mBitmapDSTHeight);
        mBitmapSRT = Bitmap.createBitmap((int) mBitmapDSTWidth, (int) mBitmapDSTHeight, Bitmap.Config.ARGB_8888);
        mBitmapSRT.eraseColor(mPointerColor);
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        // 绘制扇形path
        mArcPath = new Path();

        //绘制运动的粒子
        mPointList.clear();
        AnimPoint mAnimPoint = new AnimPoint();
        for (int i = 0; i < pointCount; i++) {
            //通过clone创建对象，避免重复创建
            AnimPoint cloneAnimPoint = mAnimPoint.clone();
            cloneAnimPoint.init(mRandom, mRadius - mOutCircleStrokeWidth / 2F);
            mPointList.add(cloneAnimPoint);
        }
        //画运动粒子
        mPointsAnimator = ValueAnimator.ofFloat(0.1F, 1F);
        mPointsAnimator.setDuration(1000);
        mPointsAnimator.setRepeatMode(ValueAnimator.RESTART);
        mPointsAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mPointsAnimator.addUpdateListener(animation -> {
            for (AnimPoint point : mPointList) {
                point.updatePoint(mRandom, mRadius);
            }
            invalidate();
        });
        mPointsAnimator.start();

        //保温模式圆环变色旋转动画
        mOutCircleAnim = ValueAnimator.ofFloat(-90F, 270F);
        mOutCircleAnim.setDuration(4000);
        mOutCircleAnim.setRepeatMode(ValueAnimator.RESTART);
        mOutCircleAnim.setRepeatCount(ValueAnimator.INFINITE);
        mOutCircleAnim.setInterpolator(new LinearInterpolator());
        mOutCircleAnim.addUpdateListener(animation -> {
            mOutCircleAnger = (float) animation.getAnimatedValue();
        });
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //画扇形区域的运动粒子
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        //把画布裁剪成扇形
        if (!isKeepWare) {
            canvas.clipPath(mArcPath);
        }
        //画运动粒子
        for (AnimPoint animPoint : mPointList) {
            mPointPaint.setAlpha(animPoint.getAlpha());
            canvas.drawCircle(animPoint.getmX(), animPoint.getmY(),
                    animPoint.getRadius(), mPointPaint);
        }
        //画渐变色圆饼
        canvas.drawCircle(0, 0, mCenterX, mSweptPaint);
        //step 2:画底色圆
        canvas.drawCircle(0, 0, mRadius, mBackCirclePaint);
        canvas.restore();

        //画进度圆环
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        if (!isKeepWare) {
            canvas.clipPath(mArcPath);
        }
        if (isKeepWare) {
            canvas.rotate(mOutCircleAnger);
        }
        canvas.drawCircle(0, 0, mRadius, mOutCirclePaint);
        canvas.restore();

        //画指针
        if (!isKeepWare) {
            if (mPointerVisible == VISIBLE) {
                canvas.translate(mCenterX, mCenterY);
                canvas.rotate(mCurrentAngle / 10F);
                canvas.translate(-mPointerRectF.width() / 2, -mCenterY);
                mPointerLayoutId = canvas.saveLayer(mPointerRectF, mBmpPaint);
                mBitmapSRT.eraseColor(mPointerColor);
                canvas.drawBitmap(mBitmapDST, null, mPointerRectF, mBmpPaint);
                mBmpPaint.setXfermode(mXfermode);
                canvas.drawBitmap(mBitmapSRT, null, mPointerRectF, mBmpPaint);
                mBmpPaint.setXfermode(null);
                canvas.restoreToCount(mPointerLayoutId);
            }
        }
    }

    /**
     * 绘制扇形path
     *
     * @param r
     * @param startAngle
     * @param sweepAngle
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getSectorClip(float r, float startAngle, float sweepAngle) {
        mArcPath.reset();
        mArcPath.addArc(-r, -r, r, r, startAngle, sweepAngle);
        mArcPath.lineTo(0, 0);
        mArcPath.close();
    }

    /**
     * 设置当前的温度
     *
     * @param temperature       当前真实温度
     * @param targetTemperature 目标真实温度
     */
    public void setCurrentTemperature(float temperature, float targetTemperature) {
        isKeepWare = false;
        //清除进度圆环的变色SweepGradient
        mOutCirclePaint.setShader(null);

        if (progressAnim != null && progressAnim.isRunning()) {
            progressAnim.cancel();
        }
        //未达到目标温度时指针是可见的
        if (temperature < targetTemperature) {
            mPointerVisible = VISIBLE;
        }

        //把当前温度和最大温度等比转换为0~3600表示
        currentProgress = temperature / targetTemperature * 3600;
        //自定义包含各个进度对应的颜色值和进度值的属性动画，
        progressAnim = ValueAnimator.ofFloat(lastTimeProgress, currentProgress);
        progressAnim.setDuration(1500);
        progressAnim.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            mCurrentAngle = value;
            //根据当前的进度值获取圆环的颜色属性
            ProgressParameter colors = getProgressParameter(value);
            //变更进度条的颜色值
            mPointPaint.setColor(colors.getPointColor());
            mOutCirclePaint.setColor(colors.getProgressColor());
            mBackCirclePaint.setColor(colors.getBgCircleColor());
            //更改指针颜色
            mPointerColor = colors.getPointColor();
            //设置内圈变色圆的shader
            mRadialGradientColors[2] = colors.getInsideColor();
            mRadialGradientColors[3] = colors.getOutsizeColor();
            mRadialGradient = new RadialGradient(
                    0,
                    0,
                    mCenterX,
                    mRadialGradientColors,
                    mRadialGradientStops,
                    Shader.TileMode.CLAMP);
            mSweptPaint.setShader(mRadialGradient);

            //获取此时的扇形区域path，用于裁剪动画粒子的canvas
            getSectorClip(width / 2F, -90, value / 10F);
        });

        progressAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lastTimeProgress = currentProgress;
                if (mCurrentAngle >= 3600) {
                    setPointerVisible(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                lastTimeProgress = currentProgress;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        progressAnim.start();
    }

    /**
     * 保温模式
     */
    public void startKeepWare() {
        isKeepWare = true;
        //设置外环颜色渐变
        mOutCirclePaint.setShader(mSweepGradient);
        //变更进度条的颜色值
        mPointPaint.setColor(pointColor5);
        mOutCirclePaint.setColor(progressColor5);
        mBackCirclePaint.setColor(bgCircleColor5);
        //设置内圈变色圆的shader
        mRadialGradientColors[2] = insideColor5;
        mRadialGradientColors[3] = outsizeColor5;
        mRadialGradient = new RadialGradient(
                0,
                0,
                mCenterX,
                mRadialGradientColors,
                mRadialGradientStops,
                Shader.TileMode.CLAMP);
        mSweptPaint.setShader(mRadialGradient);
        //开始外圈变色旋转动画
        mOutCircleAnim.start();
        invalidate();
    }

    private float mPointerTranslation;

    /**
     * 标记指针此时是否可见
     */
    private int mPointerVisible = VISIBLE;

    /**
     * 设置指针显示或者是隐藏的动画
     *
     * @param visible = VISIBLE or GONE
     */
    private void setPointerVisible(int visible) {
        if (visible == mPointerVisible) {
            return;
        }
        if (visible == GONE) {
            //隐藏指针，在保温状态 或者 温度达到预定温度是需要隐藏
            ValueAnimator gonePointerAnimator = ValueAnimator.ofFloat(0, 1);
            gonePointerAnimator.setDuration(1000);
            gonePointerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mPointerTranslation = (float) gonePointerAnimator.getAnimatedValue();
                }
            });
            gonePointerAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mPointerVisible = GONE;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    mPointerVisible = GONE;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            gonePointerAnimator.start();
        }
    }

    /**
     * 获取当前进度值（0~3600）对应的颜色值
     *
     * @param progressValue 0~3600之间的圆环进度值
     * @return
     */
    @SuppressLint("RestrictedApi")
    private ProgressParameter getProgressParameter(float progressValue) {
        float fraction = 0F;
        if (progressValue >= 360 && progressValue < 1800) {
            fraction = progressValue % 360 / 360;
        }

        if (progressValue < 360) {
            //第一个颜色段
            mParameter.setInsideColor(insideColor1);
            mParameter.setOutsizeColor(outsizeColor1);
            mParameter.setProgressColor(progressColor1);
            mParameter.setPointColor(pointColor1);
            mParameter.setBgCircleColor(bgCircleColor1);
        } else if (progressValue < 720) {
            //第二个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor1, insideColor2));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor1, outsizeColor2));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor1, progressColor2));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor1, pointColor2));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor1, bgCircleColor2));
        } else if (progressValue < 1080) {
            //第三个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor2, insideColor3));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor2, outsizeColor3));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor2, progressColor3));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor2, pointColor3));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor2, bgCircleColor3));
        } else if (progressValue < 1440) {
            //第四个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor3, insideColor4));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor3, outsizeColor4));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor3, progressColor4));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor3, pointColor4));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor3, bgCircleColor4));

        } else if (progressValue < 1800) {
            //第五个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor4, insideColor5));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor4, outsizeColor5));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor4, progressColor5));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor4, pointColor5));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor4, bgCircleColor5));
        } else {
            mParameter.setInsideColor(insideColor5);
            mParameter.setOutsizeColor(outsizeColor5);
            mParameter.setProgressColor(progressColor5);
            mParameter.setPointColor(pointColor5);
            mParameter.setBgCircleColor(bgCircleColor5);
        }
        return mParameter;
    }

    /**
     * 初始化设置控件宽高
     *
     * @param width
     * @param height
     */
    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPointsAnimator.cancel();
        mOutCircleAnim.cancel();
    }
}
