package com.hehen.heweater.application;

import android.app.Application;

import com.hehen.heweater.db.DatabaseHelper;
import com.hehen.heweater.utils.SPUtils;

/**
 * @author chenping
 * @date 2019/2/23 5:43 PM
 * @Description:
 */
public class HeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.init(getApplicationContext(),"share_data.perf");
        DatabaseHelper.init(getApplicationContext(),"he_wedb.db");
    }
}
