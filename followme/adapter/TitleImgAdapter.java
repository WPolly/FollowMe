package com.xiaoshan.polly.followme.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class TitleImgAdapter extends PagerAdapter {

    private List<ImageView> mIvs;

    public TitleImgAdapter(List<ImageView> mIvs) {
        this.mIvs = mIvs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = mIvs.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return mIvs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
