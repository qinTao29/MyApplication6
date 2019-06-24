package cn.edu.gdpt.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.song.refresh_view.PullToRefreshView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView city_tv, info_tv, temperature_tv, search_tv,humidity_tv,direct_tv,power_tv;
    private ListView future_listview;
    private WeatherAdapter weatherAdapter;
    private List<WeatherResul.WeatherFuture> futuresList;
    private String cityName = "深圳";
    private LocationManager locationManager;
    private Location location;
    private PullToRefreshView refreshView;
    //private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //下拉刷新
        refreshView =findViewById(R.id.refreshView);
        refreshView.setColorSchemeColors(Color.RED,Color.BLUE); // 颜色
        refreshView.setSmileStrokeWidth(8);// 设置绘制的笑脸的宽度
        refreshView.setSmileInterpolator(new LinearInterpolator());// 笑脸动画转动的插值器
        refreshView.setSmileAnimationDuration(3000); // 设置笑脸旋转动画的时长
        refreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });//设置下拉刷新监听


        city_tv = findViewById(R.id.city_tv);
        info_tv = findViewById(R.id.info_tv);
        temperature_tv = findViewById(R.id.temperature_tv);
        humidity_tv = findViewById(R.id.humidity_tv);
        direct_tv = findViewById(R.id.direct_tv);
        power_tv = findViewById(R.id.power_tv);
        search_tv = findViewById(R.id.search_tv);
        future_listview = findViewById(R.id.weather_listview);
        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        futuresList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(this, futuresList);
        future_listview.setAdapter(weatherAdapter);

        getLocation();
        getWeather();
    }

    private void requestData() {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                weatherAdapter.notifyDataSetChanged();//实现刷新效果
                Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
                refreshView.setRefreshing(false);// 请求数据完成
            }
        },3000);
    }

    //网络请求，获取天气预报
    private void getWeather() {
        //创建okHttpClient对象，封装好网络请求的数据，第三方框架：固定传参和回参的写法
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //这里是放置需要传给服务器的参数
        if (cityName != null) {
            builder.add("city", cityName);
        }
        builder.add("key", "946191298dbf4c92ac1f93293376990b");
        //这里是把url和参数封装
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/simpleWeather/query")
                .post(builder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            //这个地方是网络回调，也就是服务器传回给你的东西，是一个json字符串
            //这边传参数给服务器也是用的json字符串
            //然后收到网络回调给你的json之后，用的是Gson进行解析的
            //解析完成之后，把得到的值显示在界面上就完了
            public void onResponse(final Response response) throws IOException {
                try {
                    final String result = response.body().string();
                    Gson gson = new Gson();
                    final WeatherResponse weatherResponse = gson.fromJson(result, WeatherResponse.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                WeatherResul.WeatherRealTime weatherRealTime = weatherResponse.getResult().getRealtime();
                                city_tv.setText(weatherResponse.getResult().getCity());
                                info_tv.setText("天气状况："+weatherRealTime.getInfo());
                                temperature_tv.setText("温度："+weatherRealTime.getTemperature() + "℃");
                                humidity_tv.setText("湿度："+weatherRealTime.getHumidity());
                                direct_tv.setText("风向："+weatherRealTime.getDirect());
                                power_tv.setText("风力："+weatherRealTime.getPower());
                                futuresList = weatherResponse.getResult().getFuture();
                                weatherAdapter = new WeatherAdapter(MainActivity.this, futuresList);
                                future_listview.setAdapter(weatherAdapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    //获取当前位置经纬度
    private void getLocation(){
// 获取系统服务
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 网络定位
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });
        //定位成功
        if (null != location) {
            gecodLocation();
        }
    }
    //解析经纬度
    private void gecodLocation() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("lng", location.getLongitude() + "");
        builder.add("lat", location.getLatitude() + "");
        builder.add("key", "3bf7a00ae635e911ee92dbf668b7ac3c");
        builder.add("type", 1 + "");

        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/geo/")
                .post(builder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                try {
                    final String result = response.body().string();
                    Gson gson = new Gson();
                    final LocationResponse locationResponse = gson.fromJson(result, LocationResponse.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                String cityStr = locationResponse.getResult().getAddress();
                                if(cityStr.contains("市")){
                                    int index = cityStr.indexOf("市");
                                    String cityTemp = cityStr.substring(index-2,index);
                                    cityName = cityTemp;
                                }
                                getWeather();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (null != data) {
                cityName = data.getStringExtra("city");
                getWeather();
            }
        }
    }
}