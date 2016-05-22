package com.xiaoshan.polly.followme;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;

public class DetailCollapseActivity extends Activity {

    private CollapsingToolbarLayout ctl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_collapse);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        ctl.setCollapsedTitleTextColor(Color.WHITE);
        ctl.setExpandedTitleColor(Color.YELLOW);
        ctl.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
    }
}
