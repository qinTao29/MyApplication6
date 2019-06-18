package cn.edu.gdpt.myapplication;

import android.app.Application;
import android.graphics.Bitmap;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(1000L, TimeUnit.MILLISECONDS)
                .readTimeout(1000L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);

    }

}
