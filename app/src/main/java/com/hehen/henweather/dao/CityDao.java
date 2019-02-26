package com.hehen.henweather.dao;

import com.hehen.henweather.bean.City;
import com.hehen.henweather.db.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
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
            count = getDao().create(cities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public List<City> findByPid(int id) {
        List<City> cities = null;
        try {
            cities = getDao().queryForEq("pid", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
