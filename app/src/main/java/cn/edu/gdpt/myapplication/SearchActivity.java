package cn.edu.gdpt.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
private TextView back_tv;
private EditText city_name_edit;
private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back_tv=(TextView)findViewById(R.id.back_tv);
        city_name_edit=(EditText)findViewById(R.id.city_name_edit);
        searchBtn=(Button)findViewById(R.id.search_btn);
        //返回
        back_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
