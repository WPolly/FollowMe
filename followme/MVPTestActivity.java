package com.xiaoshan.polly.followme;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.xiaoshan.polly.followme.presenter.MVPTestPresenter;
import com.xiaoshan.polly.followme.utils.VolleyUtils;
import com.xiaoshan.polly.followme.view.IMVPTestView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MVPTestActivity extends AppCompatActivity implements IMVPTestView {

    @InjectView(R.id.iv_via_image_loader)
    ImageView mIvViaImageLoader;
    private ImageLoader mImageLoader;
    private ProgressDialog mProgressDialog;
    private MVPTestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);
        ButterKnife.inject(this);
        mImageLoader = VolleyUtils.getInstance(this).getImageLoader();
        mPresenter = new MVPTestPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.startDialogLogic(this);
    }

    private void loadImageFromNet() {
        String url = "http://www.z990.com/uploadfile/2014/0417/20140417111043259.jpg";
        mImageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap bitmap = response.getBitmap();
                mIvViaImageLoader.setImageBitmap(bitmap);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MVPTestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // region presenter begin
    @Override
    public void showLoadingDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在检查网络..");
        mProgressDialog.show();
    }

    @Override
    public void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("网络连接良好,是否加载图片?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadImageFromNet();
            }
        }).setNegativeButton("No", null).show();
    }

    @Override
    public void showNetWorkError() {
        Toast.makeText(this, "网络连接失败,请检查网络.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoadingDialog() {
        mProgressDialog.dismiss();
    }
    // region presenter end
}
