package com.hehen.henweather.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author chenping
 * @date 2019/2/27 4:55 AM
 * @Description:
 */
@DatabaseTable(tableName = "tb_weather")
public class Weather implements Serializable {
    @DatabaseField(generatedId = true)
    private  int id;
    @DatabaseField
    private String date ;
    @DatabaseField
    private String sunrise;
    @DatabaseField
    private String high;
    @DatabaseField
    private String low ;
    @DatabaseField
    private String sunset;
    @DatabaseField
    private String aqi;
    @DatabaseField
    private String ymd;
    @DatabaseField
    private String week;
    @DatabaseField
    private String fx;
    @DatabaseField
    private String fl;
    @DatabaseField
    private String type;
    @DatabaseField
    private String notice;
    @DatabaseField(columnName = "forecast_id",foreignAutoCreate = true,foreign = true,foreignAutoRefresh = true)
    private  Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", sunset='" + sunset + '\'' +
                ", aqi='" + aqi + '\'' +
                ", ymd='" + ymd + '\'' +
                ", week='" + week + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
