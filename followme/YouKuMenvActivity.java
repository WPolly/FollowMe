package com.xiaoshan.polly.followme;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoshan.polly.followme.adapter.TitleImgAdapter;
import com.xiaoshan.polly.followme.utils.YouKuMenuTools;
import com.xiaoshan.polly.followme.widget.MyToggle;

import java.util.ArrayList;
import java.util.List;

public class YouKuMenvActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LOOPING = 1;
    private boolean isLevel1Show = true;
    private boolean isLevel2Show = true;
    private boolean isLevel3Show = true;
    private boolean isLooping = true;
    private RelativeLayout rlLevel1;
    private RelativeLayout rlLevel2;
    private RelativeLayout rlLevel3;
    private ImageView ivHome;
    private ImageView ivMenu;
    private ViewPager vpTitleImg;
    private LinearLayout llPointContainer;
    private TextView tvImgTitle;

    private int[] imgIds = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    private String[] imageDescriptions = {
            "巩俐不低俗，我就不低俗",
            "朴树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀"};
    private List<ImageView> imgs;

    private int currentItem;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == LOOPING) {
                if (currentItem < (imgIds.length-1)) {
                    currentItem ++;
                    vpTitleImg.setCurrentItem(currentItem, true);
                } else {
                    currentItem = 0;
                    vpTitleImg.setCurrentItem(currentItem, true);
                }
                sendEmptyMessageDelayed(LOOPING,2000);
            }
        }
    };
    private MyToggle myToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_ku_menv);
        initView();
        initData();
        initListener();
        vpTitleImg.setAdapter(new TitleImgAdapter(imgs));
        tvImgTitle.setText(imageDescriptions[0]);
    }

    private void initData() {
        imgs = new ArrayList<>();

        for (int id : imgIds) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(id);
            imgs.add(imageView);
            ImageView ivPoint = new ImageView(this);
            ivPoint.setImageResource(R.drawable.point_indicator);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, -2);
            params.rightMargin = 15;//设置距离
            ivPoint.setLayoutParams(params);
            llPointContainer.addView(ivPoint);
            if (id == imgIds[0]) {
                ivPoint.setEnabled(true);
                continue;
            }
            ivPoint.setEnabled(false);
        }
        currentItem = 0;
        mHandler.sendEmptyMessageDelayed(LOOPING,3000);
        myToggle.setToggleStatus(true);

    }

    private void initView() {
        rlLevel1 = ((RelativeLayout) findViewById(R.id.rl_level1));
        rlLevel2 = ((RelativeLayout) findViewById(R.id.rl_level2));
        rlLevel3 = ((RelativeLayout) findViewById(R.id.rl_level3));
        ivHome = ((ImageView) findViewById(R.id.iv_home));
        ivMenu = ((ImageView) findViewById(R.id.iv_menu));
        myToggle = (MyToggle) findViewById(R.id.mytoggle);
        vpTitleImg = ((ViewPager) findViewById(R.id.vp_title_img));
        llPointContainer = (LinearLayout) findViewById(R.id.point_container);
        tvImgTitle = ((TextView) findViewById(R.id.tv_img_title));
    }

    private void initListener() {
        ivHome.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        vpTitleImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvImgTitle.setText(imageDescriptions[position]);
                int childCount = llPointContainer.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    llPointContainer.getChildAt(i).setEnabled(i == position);
                }
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopLooping();
                        myToggle.setToggleStatus(false);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        restartLooping();
                        myToggle.setToggleStatus(true);
                        break;
                }
            }
        });

        myToggle.setOnToggleChagedListener(new MyToggle.onToggleChangedListener() {
            @Override
            public void toggleOn() {
                restartLooping();
            }

            @Override
            public void toggleOff() {
                stopLooping();
            }
        });
    }

    private void restartLooping() {
        if (!isLooping) {
            mHandler.sendEmptyMessageDelayed(LOOPING,2000);
            isLooping = true;
        }
    }

    private void stopLooping() {
        if (isLooping) {
            mHandler.removeMessages(LOOPING);
            isLooping = false;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_home:

                if (isLevel2Show) {
                    if (isLevel3Show) {
                        YouKuMenuTools.hideMenu(rlLevel2);
                        isLevel3Show = false;
                        YouKuMenuTools.hideMenu(rlLevel3, 200);
                        isLevel2Show = false;
                    } else {
                        YouKuMenuTools.hideMenu(rlLevel2);
                        isLevel2Show = false;
                    }

                } else {
                    YouKuMenuTools.showMenu(rlLevel2);
                    isLevel2Show = true;
                }

                break;

            case R.id.iv_menu:
                if (isLevel3Show) {
                    YouKuMenuTools.hideMenu(rlLevel3);
                    isLevel3Show = false;
                } else {
                    YouKuMenuTools.showMenu(rlLevel3);
                    isLevel3Show = true;
                }

                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (isLevel1Show) {
                if (isLevel3Show) {
                    YouKuMenuTools.hideMenu(rlLevel1);
                    isLevel1Show = false;
                    YouKuMenuTools.hideMenu(rlLevel2, 200);
                    isLevel2Show = false;
                    YouKuMenuTools.hideMenu(rlLevel3, 400);
                    isLevel3Show = false;
                } else if (isLevel2Show) {
                    YouKuMenuTools.hideMenu(rlLevel1);
                    isLevel1Show = false;
                    YouKuMenuTools.hideMenu(rlLevel2, 200);
                    isLevel2Show = false;
                } else {
                    YouKuMenuTools.hideMenu(rlLevel1);
                    isLevel1Show = false;
                }
            } else {
                YouKuMenuTools.showMenu(rlLevel1);
                isLevel1Show = true;
                YouKuMenuTools.showMenu(rlLevel2, 200);
                isLevel2Show = true;
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
