package com.xiaoshan.polly.followme.utils;

import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/1/3.
 */
public class YouKuMenuTools {
    public static void showMenu(ViewGroup vg) {
        RotateAnimation ra = new RotateAnimation(180,360,(vg.getWidth())/2,vg.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        vg.startAnimation(ra);
        enableChildView(vg);
    }

    public static void hideMenu(ViewGroup vg) {
        RotateAnimation ra = new RotateAnimation(0,180,(vg.getWidth())/2,vg.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        vg.startAnimation(ra);
        disableChildView(vg);
    }

    public static void hideMenu(ViewGroup vg, long startOffset) {
        RotateAnimation ra = new RotateAnimation(0,180,(vg.getWidth())/2,vg.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);
        vg.startAnimation(ra);
        disableChildView(vg);
    }

    private static void disableChildView(ViewGroup vg) {
        int childCount = vg.getChildCount();
        for (int i=0; i<childCount; i++) {
            vg.getChildAt(i).setEnabled(false);
        }
    }

    private static void enableChildView(ViewGroup vg) {
        int childCount = vg.getChildCount();
        for (int i=0; i<childCount; i++) {
            vg.getChildAt(i).setEnabled(true);
        }
    }

    public static void showMenu(ViewGroup vg, long startOffset) {
        RotateAnimation ra = new RotateAnimation(180,360,(vg.getWidth())/2,vg.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffset);
        vg.startAnimation(ra);
        enableChildView(vg);
    }
}
