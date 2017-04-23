package top.wuhaojie.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by wuhaojie on 2016/7/15 11:36.
 */
class ScrollNumber extends View {

    public static final String TAG = "ScrollNumber";
    /**
     * default animation velocity
     */
    public static final int DEFAULT_VELOCITY = 15;
    /**
     * number to - number from
     */
    private int mDeltaNum;
    /**
     * the current showing number
     */
    private int mCurNum;
    /**
     * the next showing number
     */
    private int mNextNum;
    /**
     * the target number
     */
    private int mTargetNum;
    private Context mContext;

    /**
     * number offset
     */
    private float mOffset;
    private Paint mPaint;
    private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();


    private float mTextCenterX;
    private int mTextHeight;
    private Rect mTextBounds = new Rect();
    private int mTextSize = sp2px(130);
    private int mTextColor = 0xFF000000;
    private Typeface mTypeface;


    private int mVelocity = DEFAULT_VELOCITY;

    public ScrollNumber(Context context) {
        this(context, null);
    }

    public ScrollNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

        if (mTypeface != null) mPaint.setTypeface(mTypeface);

        measureTextHeight();

    }

    public void setVelocity(@IntRange(from = 0, to = 1000) int velocity) {
        mVelocity = velocity;
    }

    public void setNumber(final int from, final int to, long delay) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setFromNumber(from);
                setTargetNumber(to);
                mDeltaNum = to - from;
            }
        }, delay);
    }

    public void setTextSize(int textSize) {
        this.mTextSize = sp2px(textSize);
        mPaint.setTextSize(mTextSize);
        measureTextHeight();
        requestLayout();
        invalidate();
    }


    public void setTextFont(String fileName) {
        if (TextUtils.isEmpty(fileName))
            throw new IllegalArgumentException("please check file name end with '.ttf' or '.otf'");
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), fileName);
        if (mTypeface == null) throw new RuntimeException("please check your font!");
        mPaint.setTypeface(mTypeface);
        requestLayout();
        invalidate();
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mPaint.setColor(mTextColor);
        invalidate();
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    private void measureTextHeight() {
        mPaint.getTextBounds(mCurNum + "", 0, 1, mTextBounds);
        mTextHeight = mTextBounds.height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);

        mTextCenterX = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) >>> 1;
    }

    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mPaint.getTextBounds("0", 0, 1, mTextBounds);
                result = mTextBounds.height();
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingTop() + getPaddingBottom() + dp2px(40);
    }

    private int measureWidth(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mPaint.getTextBounds("0", 0, 1, mTextBounds);
                result = mTextBounds.width();
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingLeft() + getPaddingRight() + 15;
    }


    @Override
    protected void onDraw(Canvas canvas) {


        if (mCurNum != mTargetNum) {
            postDelayed(mScrollRunnable, 0);
        }

//        Log.d(TAG, "onDraw: curr=" + mCurNum + " target=" + mTargetNum + " offset=" + mOffset);

        canvas.translate(0, mOffset * getMeasuredHeight());
        drawSelf(canvas);
        drawNext(canvas);
//        canvas.restore();
    }

    private void setFromNumber(int number) {
        if (number < 0 || number > 9)
            throw new RuntimeException("invalidate number , should in [0,9]");
        calNum(number);
        mOffset = 0;
        invalidate();
    }


    private void calNum(int number) {
        number = number == -1 ? 9 : number;
        number = number == 10 ? 0 : number;
        mCurNum = number;
        mNextNum = number + 1 == 10 ? 0 : number + 1;
    }

    private Runnable mScrollRunnable = new Runnable() {
        @Override
        public void run() {
            float x = (float) (1 - 1.0 * (mTargetNum - mCurNum) / mDeltaNum);
//            mOffset -= 0.15f * (1 - mInterpolator.getInterpolation(x) + 0.1);
            mOffset -= mVelocity * 0.01f * (1 - mInterpolator.getInterpolation(x) + 0.1);
            invalidate();

            if (mOffset <= -1) {
                mOffset = 0;
                calNum(mCurNum + 1);
            }


        }
    };


    private void drawNext(Canvas canvas) {
        float y = (float) (getMeasuredHeight() * 1.5);
        canvas.drawText(mNextNum + "", mTextCenterX, y + mTextHeight / 2, mPaint);
    }

    private void drawSelf(Canvas canvas) {
        int y = getMeasuredHeight() / 2;
        canvas.drawText(mCurNum + "", mTextCenterX, y + mTextHeight / 2, mPaint);
    }


    public void setTargetNumber(int nextNum) {
        this.mTargetNum = nextNum;
        invalidate();
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    private int sp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dpVal, getResources().getDisplayMetrics());
    }


}
