package com.hehen.henweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hehen.henweather.utils.data.DataUtils;
import com.hehen.henweather.utils.other.SPUtils;
import com.j256.ormlite.stmt.query.In;

public class MainActivity extends AppCompatActivity {

    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_text = findViewById(R.id.id_tv_text);
        toChooeseActivity();
    }

    private void toChooeseActivity() {
        Intent intent = new Intent(MainActivity.this, ChooseItemActivity.class);
        startActivityForResult(intent, 2001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2003) {
            //加载天气
            tv_text.setText(DataUtils.selecedCity.toString());
        }
    }
}
