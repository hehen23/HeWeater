package com.hehen.heweater.service.dao;

import android.util.Log;
import android.widget.Toast;

import com.hehen.heweater.bean.City;
import com.hehen.heweater.config.Config;
import com.hehen.heweater.db.DatabaseHelper;
import com.hehen.heweater.utils.SPUtils;
import com.hehen.heweater.utils.T;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * @author chenping
 * @date 2019/2/25 11:03 AM
 * @Description:
 */
public class CityDao {
    public DatabaseHelper getHelper() {
        return DatabaseHelper.getInstance();
    }

    public Dao<City, Integer> getCityDao() throws SQLException {
        return getHelper().getDao(City.class);
    }

    public void insertCity(List<City> cities) {
        try {
            Dao<City, Integer> cityIntegerDao = getCityDao();
            int count = cityIntegerDao.create(cities);
            if (count > 0) {
                SPUtils.getInstance().put(Config.CITY_KEY, true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<City> findByProvinceList() {
        List<City> list = null;
        try {
            Dao<City, Integer> dao = getCityDao();
            list = dao.queryForEq("pid", 0);
        } catch (SQLException e) {
            Log.i("TAG", "findByProvinceList:加载出错啦");
            e.printStackTrace();
        }
        return list;
    }

    public List<City> findByCityList(City city) {
        List<City> list;
        try {
            Dao<City,Integer> dao = getCityDao();
            int id = city.getId();
            Log.i(TAG, "findByCityList:id "+id);
            list = dao.queryForEq("pid",id);
            Log.i(TAG, "findByCityList:size "+list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
