package com.xiaoshan.polly.followme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView netImg;
    private ImageView localImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        netImg = (ImageView) findViewById(R.id.iv_gif_net);
        localImg = (ImageView) findViewById(R.id.iv_gif_local);

        Glide.with(this).load("http://att.bbs.duowan.com/forum/201303/27/015233vmqvo0bvqazo1z1b.gif").into(netImg);
        Glide.with(this).load(R.drawable.cat).into(localImg);
    }
}
