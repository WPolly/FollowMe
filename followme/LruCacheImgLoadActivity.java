package com.xiaoshan.polly.followme;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LruCacheImgLoadActivity extends AppCompatActivity {

    @InjectView(R.id.lv_net_img_list)
    ListView mLvNetImgList;

    private List<String> mCacheImgUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru_cache_img_load);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        mCacheImgUrls = new ArrayList<>();
        String baseUrl = "http://image31.360doc.com/DownloadImg/2011/07/0308/13732751_";
        for (int i = 1; i < 36; i++) {
            String url = baseUrl + i + ".jpg";
            mCacheImgUrls.add(url);
        }

        mLvNetImgList.setAdapter(new CacheImgAdapter());
    }

    class CacheImgAdapter extends BaseAdapter {

        private MyLruImgCache mImageCache = new MyLruImgCache();
        private RequestQueue mQueue = Volley.newRequestQueue(LruCacheImgLoadActivity.this);
        private ImageLoader mImageLoader = new ImageLoader(mQueue, mImageCache);


        @Override
        public int getCount() {
            return mCacheImgUrls.size();
        }

        @Override
        public Object getItem(int position) {
            return mCacheImgUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NetworkImageView imageView;
            if (convertView == null) {
                imageView = new NetworkImageView(LruCacheImgLoadActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                int padding = 5;
                imageView.setPadding(padding, padding, padding, padding);
                convertView = imageView;
            } else {
                imageView = (NetworkImageView) convertView;
            }
            imageView.setDefaultImageResId(R.mipmap.ic_launcher);
            imageView.setErrorImageResId(R.mipmap.slide_button);
            imageView.setImageUrl(mCacheImgUrls.get(position), mImageLoader);
            return convertView;
        }
    }

    class MyLruImgCache implements ImageLoader.ImageCache {

        private int maxSize = 10 * 1024 * 1024;
        private LruCache<String, Bitmap> mLruCache;

        public MyLruImgCache() {
            mLruCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    System.out.println(value.getByteCount());
                    return value.getByteCount();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            System.out.println("取缓存");
            return mLruCache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            System.out.println("写缓存");
            mLruCache.put(s,bitmap);
        }
    }
}
