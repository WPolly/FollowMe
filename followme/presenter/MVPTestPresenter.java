package com.xiaoshan.polly.followme.presenter;

import android.content.Context;
import android.os.Handler;

import com.xiaoshan.polly.followme.model.INetWork;
import com.xiaoshan.polly.followme.model.impl.NetWorkImpl;
import com.xiaoshan.polly.followme.view.IMVPTestView;

/**
 * Created by xiaoshan on 2016/3/7.
 * 22:52
 */
public class MVPTestPresenter {

    private INetWork mNetWork;
    private IMVPTestView mMVPTestView;
    private Handler mHandler = new Handler();

    public MVPTestPresenter(IMVPTestView MVPTestView) {
        mMVPTestView = MVPTestView;
        mNetWork = new NetWorkImpl();
    }

    public void startDialogLogic(final Context context) {
        mMVPTestView.showLoadingDialog();
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mNetWork.isNetAvailable(context)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mMVPTestView.showSuccessDialog();
                        }
                    });

                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mMVPTestView.showNetWorkError();
                        }
                    });
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mMVPTestView.hideLoadingDialog();
                    }
                });
            }
        }.start();

    }
}
