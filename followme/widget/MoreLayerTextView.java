package com.xiaoshan.polly.followme.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiaoshan on 2016/1/16.
 * 19:23
 */
public class MoreLayerTextView extends TextView {

    private Paint mPaint1;
    private Paint mPaint2;

    public MoreLayerTextView(Context context) {
        super(context);
        initPaint();
    }

    public MoreLayerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getMeasuredWidth()+10,getMeasuredHeight()+10,mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth(),getMeasuredHeight(),mPaint2);
        //canvas.save();
        //canvas.translate(10,0);
        super.onDraw(canvas);
        //canvas.restore();
    }
}
