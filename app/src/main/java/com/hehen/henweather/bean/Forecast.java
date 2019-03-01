package com.hehen.henweather.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author chenping
 * @date 2019/2/27 5:09 AM
 * @Description:
 */
@DatabaseTable(tableName = "tb_forecast")
public class Forecast implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String city_code;



    @ForeignCollectionField
    private Collection<Weather> weathers;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Collection<Weather> getWeathers() {
        return weathers;
    }
    public void setWeathers(Collection<Weather> weathers) {
        this.weathers = weathers;
    }
    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

}
