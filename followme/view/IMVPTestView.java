package com.xiaoshan.polly.followme.view;

/**
 * Created by xiaoshan on 2016/3/7.
 * 22:47
 */
public interface IMVPTestView {
    /**显示加载框*/
    void showLoadingDialog();

    /** 显示成功提示框*/
    void showSuccessDialog();

    /**提示网络错误*/
    void showNetWorkError();

    /**隐藏加载框*/
    void hideLoadingDialog();
}
