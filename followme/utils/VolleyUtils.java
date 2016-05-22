package com.xiaoshan.polly.followme.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by xiaoshan on 2016/3/7.
 * 22:11
 */
public class VolleyUtils {
    private static VolleyUtils instance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private LruImgCache mLruImgCache;

    private VolleyUtils(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mLruImgCache = new LruImgCache();
        mImageLoader = new ImageLoader(mRequestQueue, mLruImgCache);
    }

    public static VolleyUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (VolleyUtils.class) {
                if (instance == null) {
                    instance = new VolleyUtils(context);
                }
            }
        }

        return instance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public LruImgCache getLruImgCache() {
        return mLruImgCache;
    }
}
