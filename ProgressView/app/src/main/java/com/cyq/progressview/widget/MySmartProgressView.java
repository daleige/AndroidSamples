package com.cyq.progressview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.cyq.progressview.R;
import com.cyq.progressview.evaluator.ProgressParameter;
import com.cyq.progressview.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 14:24
 * desc   : 温度进度控件
 */
@SuppressLint("RestrictedApi")
public class MySmartProgressView extends View {
    /**
     * 控件宽高
     */
    private int width, height;
    /**
     * 粒子总个数
     */
    private int pointCount = 150;
    /**
     * 粒子列表
     */
    private List<AnimPoint> mPointList = new ArrayList<>(pointCount);
    /**
     * 粒子外层圆环原点坐标和半径长度
     */
    private int mCenterX, mCenterY, mRadius;
    /**
     * 粒子外层圆环的画笔
     */
    private Paint mOutCirclePaint;
    /**
     * 粒子画笔
     */
    private Paint mPointPaint;
    /**
     * 底色圆环画笔
     */
    private Paint mBackCirclePaint;
    /**
     * 开始时底色圆环渐变的画笔
     */
    private Paint mBackShadePaint;
    /**
     * 底色圆环初始化动画渐变色
     */
    private int[] backShaderColorArr;
    private float[] backPositionArr = {0, 0, 1};
    /**
     * 内环到外环的颜色变化数字
     */
    private int[] mRadialGradientColors = new int[3];
    private float[] mRadialGradientStops = {0F, 0.5F, 1F};
    private LinearGradient mBackCircleLinearGradient;
    private Paint mSweptPaint;
    private RadialGradient mRadialGradient;
    private Random mRandom = new Random();
    /**
     * 宽高等于控件大小额矩形
     */
    private RectF mRect;
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
    private final int mOutCircleStrokeWidth = 25;
    /**
     * 底色环的边框大小
     */
    private final int mBackCircleStrokeWidth = 12;
    private float mCurrentAngle;
    /**
     * 是否是保温
     */
    private boolean isKeepWare = false;
    /**
     * 温度环的颜色属性
     */
    private int insideColor1 = Color.parseColor("#FF001BFF");
    private int outsizeColor1 = Color.parseColor("#A60067FF");
    private int progressColor1 = Color.parseColor("#FF0066FF");
    private int pointColor1 = Color.parseColor("#FF1978FF");
    private int bgCircleColor1 = Color.parseColor("#290066FF");
    private int insideColor2 = Color.parseColor("#FFFFB600");
    private int outsizeColor2 = Color.parseColor("#A6FFB600");
    private int progressColor2 = Color.parseColor("#FFFFDB00");
    private int pointColor2 = Color.parseColor("#FFFFBD00");
    private int bgCircleColor2 = Color.parseColor("#1AFFDB00");
    private int insideColor3 = Color.parseColor("#FFFF8700");
    private int outsizeColor3 = Color.parseColor("#A6FF7700");
    private int progressColor3 = Color.parseColor("#FFFFA300");
    private int pointColor3 = Color.parseColor("#FFFF9700");
    private int bgCircleColor3 = Color.parseColor("#1AFFA300");
    private int insideColor4 = Color.parseColor("#FFFF2200");
    private int outsizeColor4 = Color.parseColor("#A6FF1600");
    private int progressColor4 = Color.parseColor("#FFFF8000");
    private int pointColor4 = Color.parseColor("#FFFF5500");
    private int bgCircleColor4 = Color.parseColor("#1AFF5500");
    private ProgressParameter mParameter = new ProgressParameter();
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

    public MySmartProgressView(Context context) {
        this(context, null);
    }

    public MySmartProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySmartProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        int middleRadialGradientColor = Color.parseColor("#1A001BFF");
        //透明颜色
        int transparentColor = Color.parseColor("#00000000");
        backShaderColorArr = new int[]{transparentColor, transparentColor, middleRadialGradientColor};
        mRadialGradientColors[0] = transparentColor;
        mRadialGradientColors[1] = transparentColor;
        width = Utils.dip2px(320, getContext());
        height = Utils.dip2px(320, getContext());
        mCenterX = width / 2;
        mCenterY = height / 2;
        // 粒子圆环的宽度
        //TODO 这个20是外框距离圆环边框中点的距离，具体大小需要等UI设计图再确认
        mRadius = Utils.dip2px(300, getContext()) / 2 - 20;
        mRect = new RectF(-mCenterY, -mCenterX, mCenterY, mCenterX);
    }

    /**
     * 初始化各类画笔
     */
    private void initPaint() {
        //step1：初始化外层圆环的画笔
        mOutCirclePaint = new Paint();
        mOutCirclePaint.setStyle(Paint.Style.STROKE);
        mOutCirclePaint.setAntiAlias(true);
        mOutCirclePaint.setStrokeWidth(mOutCircleStrokeWidth);
        mOutCirclePaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));

        //step2：初始化运动粒子的画笔
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setMaskFilter(new BlurMaskFilter(3, BlurMaskFilter.Blur.NORMAL));

        //step3：内圈到外圈渐变色画笔
        mSweptPaint = new Paint();
        mSweptPaint.setAntiAlias(true);
        mSweptPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRadialGradient = new RadialGradient(
                0,
                0,
                mRadius + mOutCircleStrokeWidth / 2F,
                mRadialGradientColors,
                mRadialGradientStops,
                Shader.TileMode.CLAMP);
        mSweptPaint.setShader(mRadialGradient);

        //初始化底色圆画笔
        mBackCirclePaint = new Paint();
        mBackCirclePaint.setAntiAlias(true);
        mBackCirclePaint.setStrokeWidth(mBackCircleStrokeWidth);
        mBackCirclePaint.setAntiAlias(true);
        mBackCirclePaint.setStyle(Paint.Style.STROKE);

        //初始化底色圆得initAnimator画笔
        mBackShadePaint = new Paint();
        mBackShadePaint.setAntiAlias(true);

        //指针画笔颜色
        mBmpPaint = new Paint();
    }

    /**
     * 初始化指针图片的Bitmap
     */
    private void initBitmap() {
        mBitmapDST = BitmapFactory.decodeResource(getResources(), R.drawable.indicator);
        //初始化指针的图层混合模式
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        mPointerRectF = new RectF(0, 0, 20, 150);
        mBitmapSRT = Bitmap.createBitmap(20, 150, Bitmap.Config.ARGB_8888);
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
        AnimPoint animPoint = new AnimPoint();
        for (int i = 0; i < pointCount; i++) {
            //通过clone创建对象，避免重复创建
            AnimPoint cloneAnimPoint = animPoint.clone();
            cloneAnimPoint.init(mRandom, mRadius);
            mPointList.add(cloneAnimPoint);
        }
        //画运动粒子
        final ValueAnimator pointsAnimator = ValueAnimator.ofFloat(0.1F, 1F);
        pointsAnimator.setDuration(1000);
        pointsAnimator.setRepeatMode(ValueAnimator.RESTART);
        pointsAnimator.setRepeatCount(ValueAnimator.INFINITE);
        pointsAnimator.addUpdateListener(animation -> {
            for (AnimPoint point : mPointList) {
                point.updatePoint(mRandom, mRadius);
            }
            invalidate();
        });
        pointsAnimator.start();

        //TODO 初始化动画 还需要和设计确认具体效果
        final ValueAnimator initAnimator = ValueAnimator.ofFloat(0, 1F);
        initAnimator.setDuration(1000);
        initAnimator.setInterpolator(new AccelerateInterpolator());
        initAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            backPositionArr[1] = value;
            mBackCircleLinearGradient = new LinearGradient(
                    0,
                    -mCenterX,
                    0,
                    mCenterY,
                    backShaderColorArr,
                    backPositionArr,
                    Shader.TileMode.CLAMP);
            mBackShadePaint.setShader(mBackCircleLinearGradient);
            invalidate();
        });
        initAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //TODO 初始化动画运行完成
            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                initAnimator.start();
            }
        }, 1000);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //step:画底色圆
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        canvas.drawCircle(0, 0, mRadius, mBackCirclePaint);
        canvas.drawRect(mRect, mBackShadePaint);
        canvas.restore();

        //step2:画扇形区域的运动粒子
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        //把画布裁剪成扇形
        if (!isKeepWare) {
            canvas.clipPath(mArcPath);
        }

        //画运动粒子
        for (AnimPoint animPoint : mPointList) {
            canvas.drawCircle(animPoint.getmX(), animPoint.getmY(),
                    animPoint.getRadius(), mPointPaint);
        }
        //画进度圆环
        canvas.drawCircle(0, 0, mRadius, mOutCirclePaint);
        //画变色圆饼
        canvas.drawCircle(0, 0, mRadius + mOutCircleStrokeWidth / 2F, mSweptPaint);
        canvas.restore();

        //画指针
        if (!isKeepWare) {
            if (mPointerVisible == VISIBLE) {
                canvas.translate(mCenterX, mCenterY);
                canvas.rotate(mCurrentAngle / 10F);
                canvas.translate(-mPointerRectF.width() / 2, -mCenterY + 10);
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
            mRadialGradient = new RadialGradient(
                    0,
                    0,
                    mRadius + mOutCircleStrokeWidth / 2F,
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
        //变更进度条的颜色值
        mPointPaint.setColor(pointColor4);
        mOutCirclePaint.setColor(progressColor4);
        mBackCirclePaint.setColor(bgCircleColor4);
        //设置内圈变色圆的shader
        mRadialGradientColors[2] = insideColor4;
        mRadialGradient = new RadialGradient(
                0,
                0,
                mRadius + mOutCircleStrokeWidth / 2F,
                mRadialGradientColors,
                mRadialGradientStops,
                Shader.TileMode.CLAMP);
        mSweptPaint.setShader(mRadialGradient);
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
    private ProgressParameter getProgressParameter(float progressValue) {
        float fraction = progressValue % 1200 / 1200;
        if (progressValue < 1200) {
            //第一个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor1, insideColor2));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor1, outsizeColor2));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor1, progressColor2));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor1, pointColor2));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor1, bgCircleColor2));
        } else if (progressValue < 2400) {
            //第二个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor2, insideColor3));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor2, outsizeColor3));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor2, progressColor3));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor2, pointColor3));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor2, bgCircleColor3));
        } else if (progressValue < 3600) {
            //第三个颜色段
            mParameter.setInsideColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, insideColor3, insideColor4));
            mParameter.setOutsizeColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, outsizeColor3, outsizeColor4));
            mParameter.setProgressColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, progressColor3, progressColor4));
            mParameter.setPointColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, pointColor3, pointColor4));
            mParameter.setBgCircleColor((Integer) ArgbEvaluator.getInstance().evaluate(fraction, bgCircleColor3, bgCircleColor4));
        }
        return mParameter;
    }
}
