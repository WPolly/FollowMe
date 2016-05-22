package com.xiaoshan.polly.followme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NdkTestActivity extends AppCompatActivity {

    @InjectView(R.id.tv_show_hello_jni)
    TextView mTvShowHelloJni;

    static {
        System.loadLibrary("hello");
    }

    public native String stringFromJNI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_test);
        ButterKnife.inject(this);
        mTvShowHelloJni.setText(stringFromJNI());
    }
}
