package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author chenping
 * @date 2019/2/24 12:24 AM
 * @Description:  天气实体类
 */
@DatabaseTable(tableName = "tb_weather")
public class Weather {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String date;
    @DatabaseField
    private String hight;
    @DatabaseField
    private String low;
    @DatabaseField
    private String sunset;
    @DatabaseField
    private String api;
    @DatabaseField
    private String ymd;
    @DatabaseField
    private String week;
    @DatabaseField
    private String fx;
    @DatabaseField
    private String f1;
    @DatabaseField
    private String  type;
    @DatabaseField
    private String notice;
    @DatabaseField(columnName = "forecast_id" ,foreignAutoRefresh = true,foreign = true)
    private Forecast forecast;
    public Weather() {
    }
    /**
     *
     * @param id
     * @param date
     * @param hight
     * @param low
     * @param sunset
     * @param api
     * @param ymd
     * @param week
     * @param fx
     * @param f1
     * @param type
     * @param notice
     */
    public Weather(int id, String date, String hight, String low, String sunset,
                   String api, String ymd, String week,
                   String fx, String f1, String type, String notice) {
        this.id = id;
        this.date = date;
        this.hight = hight;
        this.low = low;
        this.sunset = sunset;
        this.api = api;
        this.ymd = ymd;
        this.week = week;
        this.fx = fx;
        this.f1 = f1;
        this.type = type;
        this.notice = notice;
    }
    /**
     *
     * @param id
     * @param date
     * @param hight
     * @param low
     * @param sunset
     * @param api
     * @param ymd
     * @param week
     * @param fx
     * @param f1
     * @param type
     * @param notice
     * @param forecast
     */
    public Weather(int id, String date, String hight, String low, String sunset,
                   String api, String ymd, String week, String fx,
                   String f1, String type, String notice, Forecast forecast) {
        this.id = id;
        this.date = date;
        this.hight = hight;
        this.low = low;
        this.sunset = sunset;
        this.api = api;
        this.ymd = ymd;
        this.week = week;
        this.fx = fx;
        this.f1 = f1;
        this.type = type;
        this.notice = notice;
        this.forecast = forecast;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
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

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
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

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
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
                "id=" + id +
                ", date='" + date + '\'' +
                ", hight='" + hight + '\'' +
                ", low='" + low + '\'' +
                ", sunset='" + sunset + '\'' +
                ", api='" + api + '\'' +
                ", ymd='" + ymd + '\'' +
                ", week='" + week + '\'' +
                ", fx='" + fx + '\'' +
                ", f1='" + f1 + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}