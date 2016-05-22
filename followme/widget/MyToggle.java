package com.xiaoshan.polly.followme.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoshan.polly.followme.R;

/**
 * Created by xiaoshan on 2016/1/7 15:48
 * 切记,学习一定要记住及时总结,不要模糊不清,模棱两可,心存侥幸.
 *
 */
public class MyToggle extends View implements View.OnClickListener {

    private Bitmap switchBg;
    private Bitmap slideButton;
    private Paint mPaint;
    private float leftDistance;
    private boolean isToggleOpen;
    private boolean isClickEnable;
    private float maxToggleDistance;
    private onToggleChangedListener mOnToggleChangedListener;

    public MyToggle(Context context) {
        super(context);
        initView();
    }

    public MyToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface onToggleChangedListener {
        void toggleOn();
        void toggleOff();
    }

    private void initView() {
        switchBg = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_background);
        slideButton = BitmapFactory.decodeResource(getResources(), R.mipmap.slide_button);
        maxToggleDistance = switchBg.getWidth() - slideButton.getWidth();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(switchBg.getWidth(), switchBg.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(switchBg, 0, 0, mPaint);
        canvas.drawBitmap(slideButton, leftDistance, 0, mPaint);
    }

    @Override
    public void onClick(View v) {
        if (isClickEnable) {
            if (isToggleOpen) {
                leftDistance = 0;
            } else {
                leftDistance = maxToggleDistance;
            }
            invalidate();
            isToggleOpen = !isToggleOpen;
            feedbackEvent();
        }
    }

    private void feedbackEvent() {
        if (mOnToggleChangedListener != null) {
           if (isToggleOpen) {
               mOnToggleChangedListener.toggleOn();
           } else {
               mOnToggleChangedListener.toggleOff();
           }
        }
    }

    private float startX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);//这句真的很重要,没有的话,不会响应任何事件 lol...

       switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClickEnable = true;
                startX = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float newX = event.getX();
                float distance = newX - startX;
                leftDistance += distance;
                if (Math.abs(leftDistance) > 5) {
                    isClickEnable = false;
                }
                leftDistance = leftDistance > maxToggleDistance ? maxToggleDistance : leftDistance;
                leftDistance = leftDistance < 0 ? 0 : leftDistance;
                startX = newX;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                if (!isClickEnable) {
                    leftDistance = leftDistance > (maxToggleDistance / 2) ? maxToggleDistance : 0;
                    invalidate();
                    isToggleOpen = (leftDistance == maxToggleDistance);
                    feedbackEvent();
                    break;
                }
        }
        return true;
    }

    public void setOnToggleChagedListener(onToggleChangedListener listener) {
            mOnToggleChangedListener = listener;
    }

    public void setToggleStatus(boolean open) {
        if (open) {
            leftDistance = maxToggleDistance;
        } else {
            leftDistance = 0;
        }
        invalidate();
        isToggleOpen = open;
    }
}
