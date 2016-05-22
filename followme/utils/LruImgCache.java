package com.xiaoshan.polly.followme.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by xiaoshan on 2016/3/7.
 * 22:18
 */
public class LruImgCache implements ImageLoader.ImageCache {

        private static final int MAXSIZE = 8 * 1024 * 1024;
        private LruCache<String, Bitmap> mLruCache;

        public LruImgCache() {
            mLruCache = new LruCache<String, Bitmap>(MAXSIZE) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    System.out.println(value.getByteCount());
                    return value.getByteCount();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mLruCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mLruCache.put(url,bitmap);
        }
}
