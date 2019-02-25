package com.hehen.heweater.utils;

import com.hehen.heweater.bean.City;
import com.hehen.heweater.bean.CurrentWeather;
import com.hehen.heweater.bean.Weather;
import com.hehen.heweater.config.Config;

import java.util.ArrayList;
import java.util.List;
/**
 * @author chenping
 * @date 2019/2/25 7:05 PM
 * @Description: 数据持久工具类
 */
public class DataUtils {
    private CurrentWeather currentWeather;
    //未来天气
    private List<Weather> forecast;
    private   List<City> provinceList;  //省份
    private  List<City> cityList;  //市列表
    private  List<City> countyList; //县列表
    private static DataUtils instance =null;
    private  boolean dataNull = false;
    private DataUtils() {
    }
    public static DataUtils getInstance(){
        if(instance == null) instance = new DataUtils();
        return instance;
    }
    public void setProvinceList(List<City> provinceList) {
        this.provinceList = provinceList;
    }
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
    public void setCountyList(List<City> countyList) {
        this.countyList = countyList;
    }


    private List<City> getProvinceList() {
        return provinceList;
    }

    private List<City> getCityList() {
        return cityList;
    }

    public List<Weather> getForecast() {
        return forecast;
    }

    public void setForecast(List<Weather> forecast) {
        this.forecast = forecast;
    }
    public boolean isDataNull() {
        return dataNull;
    }
    private List<City> getCountyList() {
        return countyList;
    }
    /**
     * 获取省分及直辖市
     * @return
     */
    public  List<City> getProvinces() {
        if(this.provinceList.isEmpty()){
            throw new IllegalArgumentException("当前数据未初始话");
        }
        return getProvinceList();
    }
    //用于设置本地数据是存在
    public void setDataNull(boolean b) {
        dataNull = b;
    }
    public List<City> getCity() {
            //数据库中
        return getCityList();
    }
    public List<City> getCountys() {
        return getCountyList();
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }


}
