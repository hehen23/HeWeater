package com.hehen.henweather.application;

import android.app.Application;

import com.hehen.henweather.db.DatabaseHelper;
import com.hehen.henweather.utils.other.SPUtils;
import com.hehen.henweather.utils.other.T;

/**
 * @author chenping
 * @date 2019/2/27 12:47 AM
 * @Description:
 */
public class App  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.init(getApplicationContext(),"hen.db");
        SPUtils.init(getApplicationContext(),"share_perf");
        SPUtils.getInstance().put("initCity",false);
        T.init(getApplicationContext());
    }
}
