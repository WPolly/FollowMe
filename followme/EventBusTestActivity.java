package com.xiaoshan.polly.followme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.xiaoshan.polly.followme.domain.UpdateBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EventBusTestActivity extends AppCompatActivity {

    @InjectView(R.id.btn_change_pic)
    Button mBtnChangePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        ButterKnife.inject(this);
//        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.btn_change_pic)
    public void onClick() {
        UpdateBean event = new UpdateBean();
        EventBus.getDefault().post(event);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

//    @Subscribe
//    public void onEvent(UpdateBean event) {
//
//    }
}
