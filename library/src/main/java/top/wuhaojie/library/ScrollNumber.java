package top.wuhaojie.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wuhaojie on 2016/7/15 11:36.
 */
public class ScrollNumber extends View {

    private Paint mPaint;

    public ScrollNumber(Context context) {
        this(context, null);
    }

    public ScrollNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawText("0", 0, 1, getWidth() / 2, getHeight() / 2, mPaint);

    }
}
