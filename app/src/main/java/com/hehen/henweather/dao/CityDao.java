package com.hehen.henweather.dao;

import com.hehen.henweather.bean.City;
import com.hehen.henweather.db.DatabaseHelper;
import com.hehen.henweather.utils.Config;
import com.hehen.henweather.utils.data.DataUtils;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenping
 * @date 2019/2/27 1:53 AM
 * @Description:
 */
public class CityDao {
    public DatabaseHelper getHelper() {
        return DatabaseHelper.getInstance();
    }

    public Dao<City, Integer> getDao() throws SQLException {
        return getHelper().getDao(City.class);
    }
    public Integer addAll(List<City> cities) {
        Integer count = null;
        try {
            if (getDao().queryForAll() != null && getDao().queryForAll().size() > 0) {
                DataUtils.setCitys(cities);
                return getDao().queryForAll().size();
            }
            count = getDao().create(cities);
            DataUtils.put(Config.CITY_LIST, cities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public List<City> findByPid(int id) {
        List<City> cities = new ArrayList<>();
        try {
            cities.addAll(getDao().queryForEq("pid", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
