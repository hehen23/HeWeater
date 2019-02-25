package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author chenping
 * @date 2019/2/24 7:47 PM
 * @Description:
 */
@DatabaseTable(tableName = "tb_forecast")
public class Forecast implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @ForeignCollectionField(columnName = "weather_id")
    private Collection<Weather> forecast;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Forecast(int id, Collection<Weather> forecast) {
        this.id = id;
        this.forecast = forecast;
    }

    public Forecast(Collection<Weather> forecast) {
        this.forecast = forecast;
    }

    public Collection<Weather> getForecast() {
        return forecast;
    }

    public void setForecast(Collection<Weather> forecast) {
        this.forecast = forecast;
    }

    public Forecast() {
    }
    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", forecast=" + forecast +
                '}';
    }
}
