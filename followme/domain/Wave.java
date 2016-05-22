package com.xiaoshan.polly.followme.domain;

import android.graphics.Paint;

/**
 * Created by xiaoshan on 2016/1/15.
 *
 */
public class Wave {

    private Paint paint;
    private float centerX;
    private float centerY;
    private float radius;

    public Wave(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
