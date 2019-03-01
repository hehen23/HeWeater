package com.hehen.henweather.utils.data;

import android.util.Log;

import com.hehen.henweather.bean.City;
import com.hehen.henweather.utils.Config;
import com.hehen.henweather.utils.other.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static android.content.ContentValues.TAG;

/**
 * @author chenping
 * @date 2019/2/27 12:59 AM
 * @Description:
 */
public class DataUtils {
    private static Map<String, Object> cache = new ConcurrentHashMap<>();
    public  static List<City> mCity = new ArrayList<>();  //所有的城市
    public static List<City> mProvince = new ArrayList<>();  //省份
    public static List<City> mCities = new ArrayList<>();   //市
    public static List<City> mCounty = new ArrayList<>(); //县/区

    public static final String KEY_PROVINCE = "key_province";
    public static final String KEY_CITY = "key_city";
    public static final String KEY_COUNTY = "key_county";

    public static  City selecedCity;

    public static City getSelecedCity() {
        return selecedCity;
    }

    public static void setSelecedCity(City selecedCity) {
        DataUtils.selecedCity = selecedCity;
    }

    public static void put(String key, Object value) {
        Log.i(TAG, "put: key = "+key+"\tvalue = "+value);
        if (null == key && key.equals("")) {
            throw new IllegalArgumentException("key is null");
        }
        cache.put(key, value);
    }
    /**
     * 设置所有的 City
     * @param citys
     */
    public static void setCitys(List<City> citys) {
        if (citys == null && citys.isEmpty()) {
            return;
        }
        mCity.addAll(citys);
    }

    public static Object get(String key) {
        return cache.get(key);
    }
    public static Map<String, Object> getCache() {
        return cache;
    }
    public static void setCounty(List<City> county) {
        if (county == null && county.isEmpty()) {
            T.showToast("数据初始化失败!");
            return;
        }
        mCounty.clear();
        mCounty.addAll(county);
    }
    public static void setCities(List<City> cities) {
        if (cities == null && cities.isEmpty()) {
            T.showToast("城市数据获取失败");
            return;
        }
        mCities.clear();
        mCities.addAll(cities);
    }
    public static void setProvince(List<City> province) {
        Log.i(TAG, "setProvince: " + province);
        mProvince.clear();
        mProvince.addAll(province);
        Log.i(TAG, "setProvince: " + mProvince);
    }
}
