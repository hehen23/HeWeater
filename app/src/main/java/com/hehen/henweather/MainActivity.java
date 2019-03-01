package com.hehen.henweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hehen.henweather.adapter.ForecastAdapter;
import com.hehen.henweather.bean.City;
import com.hehen.henweather.bean.CurrentWeather;
import com.hehen.henweather.bean.Weather;
import com.hehen.henweather.biz.CityBiz;
import com.hehen.henweather.biz.WeatherBiz;
import com.hehen.henweather.utils.Config;
import com.hehen.henweather.utils.data.DataUtils;
import com.hehen.henweather.utils.other.SPUtils;
import com.hehen.henweather.utils.other.T;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_CityName;
    private TextView tv_notice;
    private TextView tv_wendu;
    private City city = new City();
    private Weather mWeather;
    private CurrentWeather mCurrentWeather;
    private  List<Weather> mWeathers;
    private TextView reflash;
    private List<Weather> weatherList = new ArrayList<>();
    private ListView listView;
    private ForecastAdapter adapter;
    private TextView switchCity;
    private WeatherBiz weatherBiz = new WeatherBiz();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        //  toChooeseActivity();
        //弹出对话框：
        final String city_name = (String) SPUtils.getInstance().get("def_city_name"," ");
        String def_city_code = (String) SPUtils.getInstance().get("def_city_code"," ");
        if(city_name != null &&!city_name.equals(" ")){
            if(def_city_code != null &&!def_city_code.equals(" ")){
//                tv_CityName.setText(city_name);
                city.setCity_name(city_name);
                weatherBiz.Load(def_city_code, new WeatherBiz.WeatherBack() {
                    @Override
                    public void onSourcess(List<Weather> weathers, CurrentWeather currentWeather) {
                        stopProgressDialog();
                        weatherList.clear();
                        weatherList.addAll(weathers);
                        mCurrentWeather = currentWeather;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showWeather();
                            }
                        });
                    }
                    @Override
                    public void onFail(String msg) {
                        stopProgressDialog();
                    }
                });
            }
            return;
        }else{
            tv_CityName.setText("北京");
            weatherBiz.Load(Config.def_city, new WeatherBiz.WeatherBack() {
                @Override
                public void onSourcess(List<Weather> weathers, CurrentWeather currentWeather) {
                    stopProgressDialog();
                    weatherList.clear();
                    weatherList.addAll(weathers);
                    mCurrentWeather = currentWeather;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
                        }
                    });
                }
                @Override
                public void onFail(String msg) {
                    stopProgressDialog();
                }
            });
        }
        //
        adapter.notifyDataSetChanged();
    }

    private void initEvent() {
        switchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换城市
                toChooeseActivity();
            }
        });
//        reflash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showProgressDialog();
//                weatherBiz.Load(Config.def_city, new WeatherBiz.WeatherBack() {
//                    @Override
//                    public void onSourcess(List<Weather> weathers, CurrentWeather currentWeather) {
//                        weatherList.clear();
//                        weatherList.addAll(weathers);
//
//                        mCurrentWeather = currentWeather;
//                        mWeathers = weathers;
//                        //   Log.i("ATG", "onCreate: "+weatherList.toString());
//                        stopProgressDialog();
//                        // adapter.notifyDataSetChanged();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                adapter.notifyDataSetChanged();
//                                showWeather();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//
//                    }
//                });
//                adapter.notifyDataSetChanged();
//            }
//        });
    }

    private void initView() {
        tv_CityName = findViewById(R.id.id_tv_cityname);
        tv_notice = findViewById(R.id.id_tv_notice);
        tv_wendu = findViewById(R.id.id_tv_wendu);
        switchCity = findViewById(R.id.id_tv_switchCity);
        listView = findViewById(R.id.id_lt_forecast);
        adapter = new ForecastAdapter(this, R.layout.weather_item_layout, weatherList);
        listView.setAdapter(adapter);
    }

    private void toChooeseActivity() {
        Intent intent = new Intent(MainActivity.this, ChooseItemActivity.class);
        startActivityForResult(intent, 2001);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2001) {
            if (resultCode == 2003) {
                //加载天气
                final City city = (City) DataUtils.get(Config.KEY_SELECED_CITY);
                if (city != null) {
                    SPUtils.getInstance().put("def_city_name",city.getCity_name()+" ");
                    SPUtils.getInstance().put("def_city_code",city.getCity_code()+" ");
                    tv_CityName.setText(city.getCity_name() + "");
                    weatherList.clear();
                    showProgressDialog();
                    weatherBiz.Load(city.getCity_code(), new WeatherBiz.WeatherBack() {
                        @Override
                        public void onSourcess(List<Weather> weathers, CurrentWeather currentWeather) {
                            weatherList.clear();
                            weatherList.addAll(weathers);
                            mCurrentWeather = currentWeather;
                            mWeathers = weathers;
                            stopProgressDialog();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showWeather();
                                    tv_CityName.setText(city.getCity_name()+"");
                                }

                            });
                        }

                        @Override
                        public void onFail(String msg) {
                        }
                    });
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void showWeather() {
        tv_CityName.setText(city.getCity_name());
        tv_notice.setText(mCurrentWeather.getGanmao());
        tv_wendu.setText(mCurrentWeather.getWendu()+" ℃ ");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        weatherBiz.Load(Config.def_city);
    }

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("加载中...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    protected void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopProgressDialog();
    }
}
