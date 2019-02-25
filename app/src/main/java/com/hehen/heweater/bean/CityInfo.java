package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;


/**
 * @author chenping
 * @date 2019/2/24 7:37 PM
 * @Description:
 */
@DatabaseTable(tableName = "tb_cityinfo")
public class CityInfo  implements Serializable {
    //系统id
    @DatabaseField(generatedId = true)
    private int city_id;
    @DatabaseField
    private String city;
    @DatabaseField
    private String cityId;
    @DatabaseField
    private String parent;
    @DatabaseField
    private String updateTime;
    @ForeignCollectionField
    private Collection<CurrentWeather> weathers;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
