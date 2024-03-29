package cn.edu.gdpt.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.htextview.rainbow.RainbowTextView;
import com.sdsmdg.tastytoast.TastyToast;
import com.spark.submitbutton.SubmitButton;

public class SearchActivity extends AppCompatActivity {
private RainbowTextView back_tv;
private EditText city_name_edit;
//private Button searchBtn;
private SubmitButton searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back_tv=(RainbowTextView) findViewById(R.id.back_tv);
        city_name_edit=(EditText)findViewById(R.id.city_name_edit);
       // searchBtn=(Button)findViewById(R.id.search_btn);
        searchBtn=(SubmitButton)findViewById(R.id.search_btn);
        //返回
        back_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = city_name_edit.getText().toString().trim();
                if (name.isEmpty()) {
                    TastyToast.makeText(SearchActivity.this, "城市不能为空", TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("city",name);
                setResult(1,intent);
                finish();
            }
        });
    }
}
