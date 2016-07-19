package top.wuhaojie.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by wuhaojie on 2016/7/15 11:36.
 */
public class ScrollNumber extends View {

    //    private Paint mPaint;
    private Context mContext;

    private int mOffHeight = 0;
    private String mCurrText;
    private String mExpectText = "1";
    private int mDeltaNum;

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

        measureTextHeight();

        setNumber(0);

        setTargetNumber(9);

        mDeltaNum = 9 - 0;
    }


    private Paint mPaint;

    private int mPreNum;
    private int mCurNum;
    private int mNextNum;
    private int mTargetNum = 0;

    private int mTextCenterX;

    private float mFraction;

    private int mSpeed = 3;

    private int mTextHeight;
    private Rect mTextBounds = new Rect();

    private int mTextSize = sp2px(130);
    private int mTextColor = 0xFFFA6703;


    ;


    private void measureTextHeight() {
        mPaint.getTextBounds(mCurNum + "", 0, 1, mTextBounds);
        mTextHeight = mTextBounds.height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);

        mTextCenterX = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 2;
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
        return result + getPaddingTop() + getPaddingBottom();
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
        return result + getPaddingLeft() + getPaddingRight();
    }


    Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

    @Override
    protected void onDraw(Canvas canvas) {


        mPaint.setColor(mTextColor);

        if (mCurNum != mTargetNum) {
            postDelayed(mScrollRunnable, 0);
            if (mFraction <= -1) {
                mFraction = 0;
                initNum(mCurNum + 1);
            }
        }

        canvas.translate(0, mFraction * getMeasuredHeight());
        drawSelf(canvas);
        drawNext(canvas);
        canvas.restore();
    }

    public void setNumber(int number) {
        if (number < 0 || number > 9)
            throw new RuntimeException("invalidate number , should in [0,9]");
        initNum(number);
        mFraction = 0;
        invalidate();
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = dp2px(mTextSize);
        requestLayout();
        invalidate();
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        invalidate();
    }

    private void initNum(int number) {
        number = number == -1 ? 9 : number;
        number = number == 10 ? 0 : number;
        mCurNum = number;

        Log.e("TAG", mCurNum + " , " + mFraction);

        mNextNum = number + 1 == 10 ? 0 : number + 1;

    }

    private Runnable mScrollRunnable = new Runnable() {
        @Override
        public void run() {
            float x = (float) (1 - 1.0 * (mTargetNum - mCurNum) / mDeltaNum);
            Log.d("ScrollNumber", "x = " + x
                    + ";y = " + mInterpolator.getInterpolation(x));
            mFraction -= 0.15f * (1 - mInterpolator.getInterpolation(x) + 0.1);
            invalidate();
        }
    };


    private void drawNext(Canvas canvas) {
        int y = getMeasuredHeight() * 3 / 2;
        canvas.drawText(mNextNum + "", mTextCenterX, y + mTextHeight / 2,
                mPaint);
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
