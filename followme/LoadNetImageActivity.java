package com.xiaoshan.polly.followme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaoshan.polly.followme.R;
import com.xiaoshan.polly.followme.fragment.Constant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoadNetImageActivity extends AppCompatActivity {

    private ImageView ivLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_net_image);
        ivLoading = (ImageView) findViewById(R.id.iv_loading);
        getImageFromNet();
    }

    private void getImageFromNet() {


        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Constant.MyUrl.imageUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(3000);
                    urlConnection.setRequestMethod("GET");
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = urlConnection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivLoading.setImageBitmap(bitmap);
                            }
                        });
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }
}
