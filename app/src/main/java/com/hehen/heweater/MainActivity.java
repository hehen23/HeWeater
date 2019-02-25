package com.hehen.heweater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hehen.heweater.bean.City;
import com.hehen.heweater.biz.CityBiz;
import com.hehen.heweater.biz.WeatherBiz;
import com.hehen.heweater.config.Config;
import com.hehen.heweater.db.DatabaseHelper;
import com.hehen.heweater.utils.SPUtils;
import com.hehen.heweater.utils.T;

public class MainActivity extends AppCompatActivity {
    private CityBiz cityBiz;
    private TextView tv_city;
    private TextView tv_name;
    private TextView tv_desc;
    private TextView tv_wendu;
    //城市
    private City mCity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SPUtils.getInstance().put("flag","多多岛");
        SPUtils.getInstance().get(Config.CITY_KEY,false);
        DatabaseHelper.getInstance();
        cityBiz = new CityBiz();
        cityBiz.getCity();

        initView();
        initEvent();
    }
    private void initEvent() {
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = (Boolean) SPUtils.getInstance().get(Config.CITY_KEY,false);
                if(flag){
                    toCityActivity();
                }else{
                    T.showToast("数据初始化中～～请稍后设置城市");
                    return;
                }
            }
        });
    }
    private void toCityActivity() {
         cityBiz.getProvinces();
        //选中城市时，加载数据省份数据
        Intent intent = new Intent(MainActivity.this,CitysActivity.class);
       startActivityForResult(intent,2003);
    }
    private void initView() {
        tv_city = findViewById(R.id.id_tv_city);
        tv_desc = findViewById(R.id.id_city_desc);
        tv_wendu = findViewById(R.id.id_city_wendu);
        tv_name = findViewById(R.id.id_city_name);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2003){
            if(resultCode == RESULT_OK){
                if(data != null){
                    mCity = (City) data.getSerializableExtra(Config.INTENT_CITY_KEY);
                    //加载数据
                    Log.i("TAG", "onActivityResult: "+mCity);
                    WeatherBiz biz = new WeatherBiz();
                    if(mCity != null){
                        //加载天气
                        biz.currentWeather(mCity.getCity_code());
                    }
                    tv_name.setText(mCity.getCity_name());
                    //
                }
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

}
