package com.hehen.heweater.service.dao;

import com.hehen.heweater.bean.City;
import com.hehen.heweater.db.DatabaseHelper;
import com.hehen.heweater.utils.SPUtils;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author chenping
 * @date 2019/2/25 11:03 AM
 * @Description:
 */
public class CityDao {
    public DatabaseHelper getHelper(){
        return DatabaseHelper.getInstance();
    }

    public Dao<City,Integer> getCityDao() throws SQLException {
        return getHelper().getDao(City.class);
    }
    public void insertCity(List<City> cities){
        try {
            Dao<City,Integer> cityIntegerDao = getCityDao();
          int count =   cityIntegerDao.create(cities);
          if(count>0){
              SPUtils.getInstance().put("cityFlag",true);
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
