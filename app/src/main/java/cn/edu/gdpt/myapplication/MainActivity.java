package cn.edu.gdpt.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

    }

    private void initData() {
        String url=CONFIG.API.URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("TAG","请求成功"+response);
                        processData(response);
                    }
                });
    }
    private void processData(String json){
        JsonBean jsonBean= JSON.parseObject(json,JsonBean.class);
        //当天天气情况
        String cityNmae=jsonBean.getResult().getCity();
        Log.d("tt","城市名："+cityNmae);
        String citytemperature=jsonBean.getResult().getRealtime().getTemperature();
        Log.d("cc","温度："+citytemperature+"℃");
        String cityhumidity=jsonBean.getResult().getRealtime().getHumidity();
        Log.d("cx","湿度："+cityhumidity);
        String cityinfo=jsonBean.getResult().getRealtime().getInfo();
        Log.d("tx","天气情况："+cityinfo);
        String citydirect=jsonBean.getResult().getRealtime().getDirect();
        Log.d("fx","风向："+citydirect);
        String citypower=jsonBean.getResult().getRealtime().getPower();
        Log.d("fs","风速："+citypower);
        //未来天气情况


    }
}