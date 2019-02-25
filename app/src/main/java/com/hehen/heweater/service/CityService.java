package com.hehen.heweater.service;

import android.util.Log;

import com.hehen.heweater.bean.City;
import com.hehen.heweater.biz.CityBiz;
import com.hehen.heweater.config.Config;
import com.hehen.heweater.service.dao.CityDao;
import com.hehen.heweater.utils.DataUtils;
import com.hehen.heweater.utils.SPUtils;
import com.hehen.heweater.utils.T;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * @author chenping
 * @date 2019/2/25 11:03 AM
 * @Description:
 */
public class CityService {
    private CityDao cityDao;
    /**
     * 添加城市列表
     *
     * @param cities
     */
    public void addCitys(List<City> cities) {
        //处理citys逻辑问题
        if (cities.isEmpty() && cities.size() == 0) {
            throw new IllegalArgumentException("参数为空：List<City> citys");
        }
        cityDao = new CityDao();
        cityDao.insertCity(cities);
        T.showToast("初始数据加载完成!");
        SPUtils.getInstance().put(Config.CITY_KEY, true);
    }

    public void getPrivince() {
        List<City> list = cityDao.findByProvinceList();
        //首次数据可能存在未加载，则直接返回
        if (list == null) {
            return;
        }
        if (list.isEmpty() && list.size() == 0) {
            Log.i("TAG", "getPrivince: 未查询到符合的数据,");
            SPUtils.getInstance().put(Config.CITY_KEY, false);  //需要重服务端获取
            DataUtils.getInstance().setDataNull(false);
        } else {
            //表示数据获取成功
            DataUtils.getInstance().setDataNull(true);
            DataUtils.getInstance().setProvinceList(list);
        }
    }
    //获取市
    public void getCity(City city) {
        Log.i(TAG, "getCity: asdfjaks " + city);
        cityDao = new CityDao();
        if (city == null) {
            return;
        }
        List<City> list = cityDao.findByCityList(city);
        DataUtils.getInstance().setCityList(list);
    }
    //查询所有数据
    public List<City> getAll(){
        cityDao =new CityDao();
        return  cityDao.findAll();
    }
    //
    //获取市
    public void getCountys(City city) {
        Log.i(TAG, "getCity: asdfjaks " + city);
        cityDao = new CityDao();
        if (city == null) {
            return;
        }
        List<City> list = cityDao.findByCityList(city);
        DataUtils.getInstance().setCountyList(list);
    }
}
