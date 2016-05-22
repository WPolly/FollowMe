package com.xiaoshan.polly.followme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoshan.polly.followme.utils.IOUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GzipTestActivity extends AppCompatActivity {

    @InjectView(R.id.bt_get_gzip_json)
    Button mBtGetGzipJson;
    @InjectView(R.id.tv_show_gzip_json)
    TextView mTvShowGzipJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzip_test);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.bt_get_gzip_json)
    public void onClick() {
        new Thread() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                try {
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    String url = "http://mobileif.maizuo.com/city";
                    HttpGet httpGet = new HttpGet(url);
                    httpGet.addHeader("Accept-Encoding","gzip");
                    HttpResponse response = defaultHttpClient.execute(httpGet);
                    boolean isGzip = false;
                    if (response.getStatusLine().getStatusCode() == 200) {
                        Header[] headers = response.getHeaders("Content-Encoding");
                        for (Header header:headers) {
                            if (header.getValue().equals("gzip")) {
                                isGzip = true;
                            }
                        }

                        HttpEntity entity = response.getEntity();
                        String result;
                        if (isGzip) {
                            InputStream content = entity.getContent();
                            GZIPInputStream inputStream = new GZIPInputStream(content);
                            result = IOUtils.convertStreamToString(inputStream);
                        } else {
                            result = EntityUtils.toString(entity);
                        }
                        final String temp = result;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvShowGzipJson.setText(temp);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
