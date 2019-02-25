package com.hehen.heweater;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hehen.heweater.application.CityBiz;
import com.hehen.heweater.db.DatabaseHelper;
import com.hehen.heweater.utils.SPUtils;

public class MainActivity extends AppCompatActivity {
    private CityBiz cityBiz;
    private TextView tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SPUtils.getInstance().put("flag","多多岛");
        DatabaseHelper.getInstance();

        boolean cityflag = (boolean) SPUtils.getInstance().get("cityFlag",true);
        cityBiz = new CityBiz();
        cityBiz.getCity();
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCityActivity();
            }
        });
    }
    private void toCityActivity() {
        Intent intent = new Intent(MainActivity.this,CitysActivity.class);
        startActivityForResult(intent,2001);
    }
    private void initView() {
        tv_city = findViewById(R.id.id_tv_city);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2001){
            if(resultCode == RESULT_OK){
                if(data != null){
                    //TODO 刷新界面
                }
            }
        }
    }
}
