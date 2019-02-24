package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author chenping
 * @date 2019/2/24 7:41 PM
 * @Description: 当前天气
 */
@DatabaseTable(tableName = "tb_currentweather")
public class CurrentWeather implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String time;  //当前时间
    @DatabaseField
    private String date;  //当前日期
    @DatabaseField
    private CityInfo cityInfo;  //城市
    @DatabaseField(columnName = "weather_id")
    private Weather weather;  //天气
    //预测天气
    @DatabaseField(columnName = "forecast_id")
    private Forecast forecast;

}
