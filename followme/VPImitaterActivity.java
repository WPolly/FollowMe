package com.xiaoshan.polly.followme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xiaoshan.polly.followme.widget.ImitateViewPager;

public class VPImitaterActivity extends AppCompatActivity {

    private ImitateViewPager ivp;
    private RadioGroup radioGroup;
    private int[] imgIds = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5, R.mipmap.a6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpimitater);
        initView();
        initListener();
    }

    private void initListener() {
        ivp.setOnVPchangeistener(new ImitateViewPager.OnVPimitaterChangedListener() {
            @Override
            public void onVPImitaterChanged(int currentPosition) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(currentPosition);
                radioButton.toggle();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Toast.makeText(VPImitaterActivity.this, "Are you OK?"+checkedId, Toast.LENGTH_SHORT).show();
                ivp.moveTo(checkedId-1);
            }
        });
    }

    private void initView() {
        ivp = (ImitateViewPager) findViewById(R.id.ivp);
        radioGroup = ((RadioGroup) findViewById(R.id.radio_group));
        for (int imgId : imgIds) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(imgId);
            ivp.addView(iv);
        }

        for (int i=0; i<imgIds.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setPadding(6,6,6,6);
            radioGroup.addView(radioButton);
            if (i==0) {
                radioButton.toggle();
            }
        }
    }

    /**
     * ViewGroup的源码,可以参考
     *
     * 改变逻辑与监听
     *  private void setCheckedId(@IdRes int id) {
     mCheckedId = id;
     if (mOnCheckedChangeListener != null) {
     mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
     }
     }
    只改变控件状态
     private void setCheckedStateForView(int viewId, boolean checked) {
     View checkedView = findViewById(viewId);
     if (checkedView != null && checkedView instanceof RadioButton) {
     ((RadioButton) checkedView).setChecked(checked);
     }
     }
     */
}
