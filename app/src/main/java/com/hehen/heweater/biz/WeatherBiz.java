package com.hehen.heweater.biz;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.hehen.heweater.bean.CityInfo;
import com.hehen.heweater.bean.CurrentWeather;
import com.hehen.heweater.bean.Forecast;
import com.hehen.heweater.bean.Weather;
import com.hehen.heweater.bean.Yesterday;
import com.hehen.heweater.config.Config;
import com.hehen.heweater.utils.DataUtils;
import com.hehen.heweater.utils.T;
import com.hehen.heweater.utils.okhttp.CommonHttpClient;
import com.hehen.heweater.utils.okhttp.callback.CommonJsonCallback;
import com.hehen.heweater.utils.okhttp.callback.CommonStringCallback;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataHandle;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataListener;
import com.hehen.heweater.utils.okhttp.params.RequestParams;
import com.hehen.heweater.utils.okhttp.request.CommonReqeust;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * @author chenping
 * @date 2019/2/26 12:54 AM
 * @Description: 获取数据
 */
public class WeatherBiz {
    public void currentWeather(String city_code) {
        CommonHttpClient.sendRequest(CommonReqeust.createGetReqeust(Config.base_url + city_code, null), new CommonStringCallback(new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i(TAG, "onSuccess: " + responseObj.toString());
                try {
                    JSONObject object = new JSONObject((String) responseObj);
                    int status = object.getInt("status");
                    Gson gson = new Gson();
                    if (status == 200) {
                        String message = object.getString("message");
                        String date = object.getString("date");
                        String time = object.getString("time");
                        String cityInfo = object.getString("cityInfo");
                        CityInfo mCityInfo = gson.fromJson(cityInfo, CityInfo.class);
                        JSONObject data = object.getJSONObject("data");
                        //当前天气
                        String shidu = data.getString("shidu");
                        String pm25 = data.getString("pm25");
                        String pm10 = data.getString("pm10");
                        String quality = data.getString("quality");
                        String wendu = data.getString("wendu");
                        String ganmao = data.getString("ganmao");
                        String yeday = data.getString("yesterday");
                        Weather yesterday = gson.fromJson(yeday, Weather.class);
                        String weter = data.getString("forecast");  //预测天气
                        List<Weather> weathers = gson.fromJson(weter, new TypeToken<List<Weather>>() {
                        }.getType());
                        CurrentWeather currentWeather = new CurrentWeather();
                        currentWeather.setTime(time);
                        currentWeather.setDate(date);
                        currentWeather.setCityInfo(mCityInfo);
                        currentWeather.setShidu(shidu);
                        currentWeather.setPm10(pm10);
                        currentWeather.setPm25(pm25);
                        currentWeather.setQuality(quality);
                        currentWeather.setWendu(wendu);
                        currentWeather.setGanmao(ganmao);
                        currentWeather.setForecast(new Forecast(weathers));
                        currentWeather.setYesterday(new Yesterday(yesterday));
                        //添加缓存中
                        DataUtils.getInstance().setCurrentWeather(currentWeather);
                        Log.i(TAG, "onSuccess: " + currentWeather);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Object responseObj) {
                T.showToast("天气获取失败！请检查网络是否通畅");
            }
        })));
    }
}
