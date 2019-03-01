package com.hehen.henweather.dao;

import com.hehen.henweather.bean.CurrentWeather;
import com.hehen.henweather.bean.Forecast;
import com.hehen.henweather.bean.Weather;
import com.hehen.henweather.db.DatabaseHelper;
import com.hehen.henweather.utils.data.DataUtils;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author chenping
 * @date 2019/2/27 5:18 AM
 * @Description:
 */
public class WeatherDao {
    public DatabaseHelper getHelper() {
        return DatabaseHelper.getInstance();
    }

    public Dao<Weather, Integer> getWeatDao() throws SQLException {
        return getHelper().getDao(Weather.class);
    }

    public Dao<Forecast, Integer> getForeDao() throws SQLException {
        return getHelper().getDao(Forecast.class);
    }

    public Dao<CurrentWeather, Integer> getCurrentDao() throws SQLException {
        return getHelper().getDao(CurrentWeather.class);
    }

    //天气
    public void add(List<Weather> weather, CurrentWeather currentWeather) {
        Forecast forecast = new Forecast();
        forecast.setCity_code(currentWeather.getCity_code()+"");
        try {
            getForeDao().create(forecast);
            for (Weather weat : weather) {
                weat.setForecast(forecast);
                getWeatDao().create(weat);
                getCurrentDao().create(currentWeather);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getWeather(String city_code) {
        try {
            List<Forecast> forecasts  =   getForeDao().queryForEq("city_code",city_code);
            if(forecasts.size() == 0){
                return;
            }
            List<Weather>   weathers = getWeatDao().queryForEq("forecast_id", forecasts.get(0).getId());
            List<CurrentWeather> currentWeather = getCurrentDao().queryForEq("city_code",city_code);
            DataUtils.put("weathers",weathers);
            DataUtils.put("currentWeather",currentWeather.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delData(){
        try {
            getCurrentDao().deleteBuilder();
            getWeatDao().deleteBuilder();
            getForeDao().deleteBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}