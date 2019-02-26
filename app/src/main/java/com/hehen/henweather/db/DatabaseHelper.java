package com.hehen.henweather.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.hehen.henweather.bean.City;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @author chenping
 * @date 2019/2/27 12:39 AM
 * @Description:
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static DatabaseHelper instance = null;
    public static DatabaseHelper init(Context context,String fileName){
        if(instance == null){
            synchronized (DatabaseHelper.class){
                if(instance == null){
                    instance = new DatabaseHelper(context,fileName);
                }
            }
        }
        return instance;
    }

    /**
     *
     * @return  instanc
     */
    public static DatabaseHelper getInstance(){
        if(instance == null){
            throw new IllegalArgumentException("工具类未初始化;");
        }
        return instance;
    }
    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,City.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.createTable(connectionSource,City.class);
            onCreate(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
