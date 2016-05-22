package com.xiaoshan.polly.followme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OpenNetVideoActivity extends AppCompatActivity {

    @InjectView(R.id.btn_open_net_video)
    Button mBtnOpenNetVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_net_video);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_open_net_video)
    public void onClick() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse("http://v.cctv.com/flash/mp4video1/huangjinqiangdang/2010/01/02/huangjinqiangdang_h264818000nero_aac32000_20100102_1262437187617-1.mp4"), "video/*");
        startActivity(intent);
    }
}
