package com.hehen.henweather.biz;

import android.util.Log;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hehen.henweather.bean.City;
import com.hehen.henweather.bean.CurrentWeather;
import com.hehen.henweather.bean.Weather;
import com.hehen.henweather.dao.WeatherDao;
import com.hehen.henweather.utils.Config;
import com.hehen.henweather.utils.data.DataUtils;
import com.hehen.henweather.utils.http.CommonRequest;
import com.hehen.henweather.utils.http.HttpUtils;
import com.hehen.henweather.utils.http.RequestParams;
import com.hehen.henweather.utils.http.StringCallback;
import com.hehen.henweather.utils.other.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.List;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * @author chenping
 * @date 2019/2/27 4:50 AM
 * @Description:
 */
public class WeatherBiz {
    private WeatherDao dao = new WeatherDao();
    private String base_url = "http://t.weather.sojson.com/api/weather/city/";

    public void Load(final String city_code, final WeatherBack weatherBack) {
          //查询本地是否存在数据
        dao.getWeather(city_code);
        if(null != DataUtils.get("weathers")&&null!=DataUtils.get("currentWeather")){
            weatherBack.onSourcess((List<Weather>) DataUtils.get("weathers"),(CurrentWeather) DataUtils.get("currentWeather"));
            return;
        }
        StringBuilder builder = new StringBuilder(base_url);
        builder.append(city_code);
        Call call = HttpUtils.sendRequest(CommonRequest.createRequestGet(builder.toString(), null), new StringCallback() {
            @Override
            public void onError(Exception e) {
                weatherBack.onFail(e.getMessage());
            }
            @Override
            public void onSuccess(String body) {
                Log.i(TAG, "onSuccess: " + body);
                try {
                    JSONObject object = new JSONObject(body);
                    JSONObject datas = object.getJSONObject("data");
                    String forecast = datas.getString("forecast");
                    List<Weather> list = GsonUtils.getGson().fromJson(forecast, new TypeToken<List<Weather>>() {
                    }.getType());
                    DataUtils.put(Config.def_city, list);
                    CurrentWeather currentWeather = new CurrentWeather();
                    currentWeather.setGanmao(datas.getString("ganmao"));
                    currentWeather.setPm10(datas.getString("pm10"));
                    currentWeather.setPm25(datas.getString("pm25"));
                    currentWeather.setQuality(datas.getString("quality"));
                    currentWeather.setWendu(datas.getString("wendu"));
                    currentWeather.setCity_code(city_code);
                    String weathJson = datas.getString("yesterday");
                    Weather weather = GsonUtils.getGson().fromJson(weathJson, Weather.class);
//                     currentWeather.setYesterday(weather);
                    Log.i(TAG, "onSuccess: "+list);
                    dao.add(list, currentWeather);
                    weatherBack.onSourcess(list, currentWeather);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface WeatherBack {
        void onSourcess(List<Weather> weathers, CurrentWeather currentWeather);
        void onFail(String msg);
    }

}
