package com.xiaoshan.polly.followme;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class VolleyTestActivity extends AppCompatActivity {

    @InjectView(R.id.bt_string_request)
    Button mBtStringRequest;
    @InjectView(R.id.bt_json_obj_request)
    Button mBtJsonObjRequest;
    @InjectView(R.id.bt_json_array_request)
    Button mBtJsonArrayRequest;
    @InjectView(R.id.tv_volley_result)
    TextView mTvVolleyResult;
    @InjectView(R.id.bt_image_request)
    Button mBtImageRequest;
    @InjectView(R.id.iv_image_request)
    ImageView mIvImageRequest;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        ButterKnife.inject(this);
        mRequestQueue = Volley.newRequestQueue(this);
    }

    @OnClick({R.id.bt_string_request, R.id.bt_json_obj_request, R.id.bt_json_array_request})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_string_request:
                String url = "http://127.0.0.1:8090/home?index=0";
                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        mTvVolleyResult.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        mTvVolleyResult.setText(volleyError.toString());
                    }
                });
                stringRequest.setTag(getClass().getSimpleName());
                mRequestQueue.add(stringRequest);
                break;
            case R.id.bt_json_obj_request:
                String url2 = "http://127.0.0.1:8090/home?index=0";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url2, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray picture = jsonObject.optJSONArray("picture");
                        String string = picture.optString(0);
                        mTvVolleyResult.setText(string);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        mTvVolleyResult.setText(volleyError.toString());
                    }
                });
                jsonObjectRequest.setTag(getClass().getSimpleName());
                mRequestQueue.add(jsonObjectRequest);
                break;
            case R.id.bt_json_array_request:
                String url3 = "http://127.0.0.1:8090/app?index=0";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url3, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        JSONObject jsonObject = jsonArray.optJSONObject(0);
                        String des = jsonObject.optString("des");
                        mTvVolleyResult.setText(des);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = volleyError.getMessage();
                        mTvVolleyResult.setText(message);
                    }
                });
                jsonArrayRequest.setTag(getClass().getSimpleName());

                mRequestQueue.add(jsonArrayRequest);
                break;
        }
    }

    @OnClick(R.id.bt_image_request)
    public void onClick() {
        String imgUrl = "http://www.corp001.com/upload/201402/2014021413923415828.jpg";
        ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mIvImageRequest.setImageBitmap(bitmap);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mTvVolleyResult.setText(volleyError.getMessage());
            }
        });
        imageRequest.setTag(getClass().getSimpleName());

        mRequestQueue.add(imageRequest);
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(getClass().getSimpleName());
        super.onDestroy();
    }
}
