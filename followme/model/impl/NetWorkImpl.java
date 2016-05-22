package com.xiaoshan.polly.followme.model.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.xiaoshan.polly.followme.model.INetWork;

/**
 * Created by xiaoshan on 2016/3/7.
 * 22:44
 */
public class NetWorkImpl implements INetWork {

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//
//                    break;
//
//                case 1:
//
//                    break;
//            }
//        }
//    };
    @Override
    public boolean isNetAvailable(final Context context) {

//        new Thread() {
//            @Override
//            public void run() {
//                ConnectivityManager connectivityManager = (ConnectivityManager) context
//                        .getSystemService(Context.CONNECTIVITY_SERVICE);
//                if (connectivityManager == null) {
//                    mHandler.sendEmptyMessage(0);
//                } else {
//                    NetworkInfo info = connectivityManager.getActiveNetworkInfo();
//                    if (info == null) {
//                        mHandler.sendEmptyMessage(0);
//                    } else {
//                        if (info.isAvailable()) {
//                            mHandler.sendEmptyMessage(1);
//                        }
//                    }
//                }
//
//                mHandler.sendEmptyMessage(0);
//            }
//        }.start();
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else {
                if (info.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }
}
