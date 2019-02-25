package com.hehen.heweater.utils;

import com.hehen.heweater.bean.City;
import com.hehen.heweater.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenping
 * @date 2019/2/25 7:05 PM
 * @Description: 用户封装处理城市信息
 */
public class DataUtils {
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

    private List<City> getCountyList() {
        return countyList;
    }
    /**
     * 获取省分及直辖市
     *
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
}
