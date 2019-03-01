package com.hehen.henweather.bean;

import java.io.Serializable;

/**
 * @author chenping
 * @date 2019/2/27 5:48 AM
 * @Description:
 */
public class CityInfo  implements Serializable {
    private int id;
    private String city;
    private String cityId;
    private String parent;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
}
