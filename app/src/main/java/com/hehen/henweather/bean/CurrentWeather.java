package com.hehen.henweather.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author chenping
 * @date 2019/2/27 5:47 AM
 * @Description:
 */
@DatabaseTable(tableName = "tb_current")
public class CurrentWeather implements Serializable {
    @DatabaseField
    private String pm25;
    @DatabaseField
    private String city_code;
    @DatabaseField
    private String pm10;
    @DatabaseField
    private String quality;
    @DatabaseField
    private String wendu;
    @DatabaseField
    private String ganmao;

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

    public String getCity_code() {
        return city_code;
    }
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
}
