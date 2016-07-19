package top.wuhaojie.library;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhaojie on 2016/7/19 20:39.
 */
public class MultiScrollNumber extends LinearLayout {
    private Context mContext;
    private List<Integer> mNumbers = new ArrayList<>();
    private List<ScrollNumber> mScrollNumbers = new ArrayList<>();
    private int mTextSize = 130;

    private int[] mTextColors = new int[]{R.color.purple01};

    public MultiScrollNumber(Context context) {
        this(context, null);
    }

    public MultiScrollNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiScrollNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setTextSize(64);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                setNumber(2048);
                requestLayout();
                invalidate();
            }
        }, 3000);

    }

    public void setNumber(int val) {
        mNumbers.clear();
        mScrollNumbers.clear();
        removeAllViews();

        int number = val;
        while (number > 0) {
            int i = number % 10;
            mNumbers.add(i);
            number /= 10;
        }

        for (int i = mNumbers.size() - 1; i >= 0; i--) {
            ScrollNumber scrollNumber = new ScrollNumber(mContext);
            scrollNumber.setTextColor(ContextCompat
                    .getColor(mContext, mTextColors[i % mTextColors.length]));
            scrollNumber.setTextSize(mTextSize);
            scrollNumber.setNumber(0, mNumbers.get(i), i * 10);
            mScrollNumbers.add(scrollNumber);
            addView(scrollNumber);
        }
    }


    public void setTextColors(@ColorRes int[] textColors) {
        if (textColors == null || textColors.length == 0)
            throw new IllegalArgumentException("color array couldn't be empty!");
        mTextColors = textColors;
        for (int i = mScrollNumbers.size() - 1; i >= 0; i--) {
            ScrollNumber scrollNumber = mScrollNumbers.get(i);
            scrollNumber.setTextColor(ContextCompat
                    .getColor(mContext, mTextColors[i % mTextColors.length]));
        }
    }


    public void setTextSize(int textSize) {
        mTextSize = textSize;
        for (ScrollNumber s : mScrollNumbers) {
            s.setTextSize(textSize);
        }
        requestLayout();
        invalidate();
    }

}
