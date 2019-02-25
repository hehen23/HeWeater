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


}
