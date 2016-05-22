package com.xiaoshan.polly.followme.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoshan.polly.followme.domain.Wave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xiaoshan on 2016/1/15..
 *
 */
public class WaveView extends View implements Runnable {

    private List<Wave> waves;
    private int[] mColors = {Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN};

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        waves = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!waves.isEmpty()) {
            for (Wave wava : waves) {
                Paint paint = wava.getPaint();
                float centerX = wava.getCenterX();
                float centerY = wava.getCenterY();
                float radius = wava.getRadius();
                canvas.drawCircle(centerX, centerY, radius, paint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float startX = event.getX();
                float startY = event.getY();
                initWave(startX, startY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void initWave(float centerX, float centerY) {
        Wave wave = new Wave(centerX, centerY);
        wave.setRadius(10);
        Paint paint = new Paint();
        Random random = new Random();
        int colorIndex = random.nextInt(6);
        paint.setColor(mColors[colorIndex]);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) (wave.getRadius() / 2.2));
        wave.setPaint(paint);
        if (waves.isEmpty()) {
            waves.add(wave);
            postDelayed(this, 50);
        } else {
            waves.add(wave);
        }
    }

    @Override
    public void run() {
        flushWave();
        invalidate();
        if (!waves.isEmpty()) {
            postDelayed(this, 50);
        }
    }

    private void flushWave() {
        List<Wave> rmwaves = new ArrayList<>();
        for (Wave wave : waves) {
            Paint paint = wave.getPaint();
            float radius = wave.getRadius();
            int alpha = paint.getAlpha();
            if (alpha <= 0) {
                rmwaves.add(wave);
                continue;
            }
            alpha -= 3;
            radius += 5;
            paint.setAlpha(alpha);
            paint.setStrokeWidth(radius / 3);
            wave.setRadius(radius);
            wave.setPaint(paint);
        }

        waves.removeAll(rmwaves);
    }
}
