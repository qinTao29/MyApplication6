package cn.edu.gdpt.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    private List<WeatherResul.WeatherFuture> list;
    private Context context;

    public WeatherAdapter(Context context, List<WeatherResul.WeatherFuture> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_item_view, null);
        TextView textview1 = (TextView) view.findViewById(R.id.textView1);
        TextView textview2 = (TextView) view.findViewById(R.id.textView2);
        TextView textview3 = (TextView) view.findViewById(R.id.textView3);
        TextView textview4 = (TextView) view.findViewById(R.id.textView4);

        WeatherResul.WeatherFuture weatherFuture = list.get(i);
        textview1.setText(weatherFuture.getDate());
        textview2.setText(weatherFuture.getWeather());
        textview3.setText(weatherFuture.getTemperature());
        textview4.setText(weatherFuture.getDirect());
        return view;

    }
}
