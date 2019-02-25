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
    private String shidu;  //湿度
    @DatabaseField
    private String pm25; //pm2.5
    @DatabaseField
    private String pm10; //pm10
    @DatabaseField
    private String quality;  //污染程度
    @DatabaseField
    private String wendu;  //温度
    @DatabaseField
    private String ganmao;  //提醒
    @DatabaseField(columnName = "city_id" ,foreign = true,foreignAutoCreate = true)
    private CityInfo cityInfo;  //城市
    //预测天气
    @DatabaseField(columnName = "forecast_id",foreign = true,foreignAutoCreate = true)
    private Forecast forecast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }


    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;}

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", shidu='" + shidu + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", quality='" + quality + '\'' +
                ", wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", cityInfo=" + cityInfo +
                ", forecast=" + forecast +
                '}';
    }

    public CurrentWeather() {
    }
}
