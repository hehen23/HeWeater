package com.hehen.heweater.service;

import com.hehen.heweater.application.CityBiz;
import com.hehen.heweater.bean.City;
import com.hehen.heweater.service.dao.CityDao;

import java.util.List;

/**
 * @author chenping
 * @date 2019/2/25 11:03 AM
 * @Description:
 */
public class CityService {
    private CityDao cityDao;

    /**
     * 添加城市列表
     * @param cities
     */
    public void addCitys(List<City> cities){
        //处理citys逻辑问题
        if(cities.isEmpty()&&cities.size()==0){
            throw  new IllegalArgumentException("参数为空：List<City> citys");
        }
        cityDao = new CityDao();
        cityDao.insertCity(cities);
    }

}
