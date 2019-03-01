package com.hehen.henweather.dao;

import com.hehen.henweather.bean.City;
import com.hehen.henweather.bean.CurrentWeather;
import com.hehen.henweather.bean.Forecast;
import com.hehen.henweather.bean.Weather;
import com.hehen.henweather.db.DatabaseHelper;
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
    //未来5天的天气
    public void add(List<Weather> weather, CurrentWeather currentWeather) {
        Forecast forecast = new Forecast();
        try {
            getForeDao().create(forecast);
            for (Weather weat : weather) {
                weat.setForecast(forecast);
                getWeatDao().create(weat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
