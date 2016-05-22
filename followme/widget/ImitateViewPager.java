package com.xiaoshan.polly.followme.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by xiaoshan on 2016/1/9.
 */
public class ImitateViewPager extends ViewGroup {

    private GestureDetector detector;
    private Scroller mScroller;
    private float startX = 0;

    private OnVPimitaterChangedListener mOnVPchangeistener;

    public ImitateViewPager(Context context) {
        super(context);
    }

    public ImitateViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (!((currentPosition == 0 && distanceX < 0) || (currentPosition == getChildCount() - 1 && distanceX > 0)))
                    scrollBy((int) distanceX, 0);
                return false;
            }
        });

        Interpolator interpolator = new BounceInterpolator();
        mScroller = new Scroller(getContext(),interpolator);
    }

    public interface OnVPimitaterChangedListener {

        void onVPImitaterChanged(int currentPosition);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }


    public void setOnVPchangeistener(OnVPimitaterChangedListener onVPchangeistener) {
        this.mOnVPchangeistener = onVPchangeistener;
    }

    private int currentPosition;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        detector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                if (endX - startX > getWidth() / 2) {
                    currentPosition--;

                } else if (endX - startX < -getWidth() / 2) {
                    currentPosition++;

                }
                setCurrentPosition(currentPosition);
                break;

        }
        return true;
    }

    private void setCurrentPosition(int position) {
        if (position < 0) {
            currentPosition = 0;
        } else if (position > getChildCount() - 1) {
            currentPosition = getChildCount() - 1;
        } else {
//            for (int i = 0;i<100; i++) {
//                int sw = getWidth() / 100;
//                SystemClock.sleep(50);
//                scrollTo(position * sw*i, 0);
//                invalidate();
//            } 为什么在一个for循环中,srollTo()不执行?
//            System.out.println(getScrollY()+"########"+getScrollX());
            moveTo(position);
            if (mOnVPchangeistener != null) {
                mOnVPchangeistener.onVPImitaterChanged(currentPosition);
            }
           // scrollTo(currentPosition*getWidth(),0);
        }
    }

    public void moveTo(int position) {
        currentPosition = position;
        mScroller.startScroll(getScrollX(),0,currentPosition*getWidth()-getScrollX(),0,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            scrollTo(currX,0);
            invalidate();
        }
    }
}
