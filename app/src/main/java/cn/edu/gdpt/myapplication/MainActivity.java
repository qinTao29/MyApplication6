package cn.edu.gdpt.myapplication;


import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView city_tv, info_tv, temperature_tv, search_tv,humidity_tv,direct_tv,power_tv;
    private ListView future_listview;
    private WeatherAdapter weatherAdapter;
    private List<WeatherResul.WeatherFuture> futuresList;
    private String cityName = "深圳";
    private LocationManager locationManager;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city_tv = findViewById(R.id.city_tv);
        info_tv = findViewById(R.id.info_tv);
        temperature_tv = findViewById(R.id.temperature_tv);
        humidity_tv = findViewById(R.id.humidity_tv);
        direct_tv = findViewById(R.id.direct_tv);
        power_tv = findViewById(R.id.power_tv);
        search_tv = findViewById(R.id.search_tv);
        future_listview = findViewById(R.id.weather_listview);

    }

}