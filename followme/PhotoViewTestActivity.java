package com.xiaoshan.polly.followme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoshan.polly.followme.domain.UpdateBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewTestActivity extends AppCompatActivity {

    @InjectView(R.id.iv_photo_jt)
    ImageView mIvPhotoJt;
    @InjectView(R.id.btn_next)
    Button mBtnNext;
    private EventBus mEventBus;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.inject(this);
        mAttacher = new PhotoViewAttacher(mIvPhotoJt);
        mAttacher.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }


    @Subscribe
    public void onReceived(UpdateBean event) {
        mIvPhotoJt.setImageResource(event.resId);
        mAttacher.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        Intent intent = new Intent(this, EventBusTestActivity.class);
        startActivity(intent);
    }
}
