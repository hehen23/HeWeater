package com.hehen.henweather.biz;

import android.util.Log;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hehen.henweather.bean.City;
import com.hehen.henweather.dao.CityDao;
import com.hehen.henweather.utils.Config;
import com.hehen.henweather.utils.data.DataUtils;
import com.hehen.henweather.utils.http.CommonRequest;
import com.hehen.henweather.utils.http.HttpUtils;
import com.hehen.henweather.utils.http.StringCallback;
import com.hehen.henweather.utils.other.GsonUtils;
import com.hehen.henweather.utils.other.SPUtils;
import com.hehen.henweather.utils.other.T;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * @author chenping
 * @date 2019/2/27 12:56 AM
 * @Description:
 */
public class CityBiz {
    private static CityDao dao = new CityDao();
    private static String url = "http://cdn.sojson.com/_city.json";
    public static boolean initFlag = false;  //保存城市数据加载完成

    public static void initLoad() {
        boolean flag = (boolean) SPUtils.getInstance().get("initCity", true);
        if (!flag) {
//            try {
//                if(dao.getAll() != null &&!dao.getAll().isEmpty()||dao.getAll().size() >0){
//                    return;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            HttpUtils.sendRequest(CommonRequest.createRequestGet(url, null), new StringCallback() {
                @Override
                public void onError(Exception e) {
                    T.showToast(e.getMessage());
                    return;
                }
                @Override
                public void onSuccess(String body) {
                    try {
                        if (dao.getDao().queryForAll().size() > 0 || !dao.getDao().queryForAll().isEmpty()) {
                            return;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    List<City> cityList = GsonUtils.getGson().fromJson(body, new TypeToken<List<City>>() {
                    }.getType());
                    if (cityList != null || !cityList.isEmpty()) {
                        dao.addAll(cityList);
                        DataUtils.mCity.clear(); //清除
                        DataUtils.setCitys(cityList);  //添加缓存
                        SPUtils.getInstance().put("initCity", true);
                    }
                    //return;
                }
            });
        } else {
            try {
                List<City> list = dao.getAll();
                if (!list.isEmpty()) {
                    DataUtils.mCity.clear();
                    DataUtils.setCitys(list);
                    return;
                } else {
                    initLoad();
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<City> getProvince() {
        initLoad();
        List<City> list = new ArrayList<>();
        if (!DataUtils.mProvince.isEmpty()) {
            return DataUtils.mProvince;
        }else{
            try {
                list.clear();
                list.addAll(dao.findByPid(0));
                DataUtils.setProvince(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return DataUtils.mProvince;
    }

    public List<City> getcities(City cities) {
        return dao.findByPid(cities.getId());
    }

    public List<City> getCount(City city) {
        return dao.findByPid(city.getId());
    }
}
