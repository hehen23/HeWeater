package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author chenping
 * @date 2019/2/24 7:58 PM
 * @Description:
 */
@DatabaseTable(tableName = "tb_yesterday")
public class Yesterday  implements Serializable {
    private int id;
    @DatabaseField(columnName = "weather_id",foreign = true,foreignAutoCreate = true)
    private Weather weather;

    public Yesterday(int id, Weather weather) {
        this.id = id;
        this.weather = weather;
    }

    public Yesterday(Weather weather) {
        this.weather = weather;
    }

    public Yesterday() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Yesterday{" +
                "id=" + id +
                ", weather=" + weather +
                '}';
    }
}
