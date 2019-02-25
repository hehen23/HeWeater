package com.hehen.heweater.biz;

import android.util.Log;

import com.bumptech.glide.load.HttpException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hehen.heweater.bean.City;
import com.hehen.heweater.config.Config;
import com.hehen.heweater.service.CityService;
import com.hehen.heweater.utils.SPUtils;
import com.hehen.heweater.utils.T;
import com.hehen.heweater.utils.okhttp.CommonHttpClient;
import com.hehen.heweater.utils.okhttp.callback.CommonJsonCallback;
import com.hehen.heweater.utils.okhttp.callback.CommonStringCallback;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataHandle;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataListener;
import com.hehen.heweater.utils.okhttp.params.RequestParams;
import com.hehen.heweater.utils.okhttp.request.CommonReqeust;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * @author chenping
 * @date 2019/2/25 12:37 PM
 * @Description:
 */
public class CityBiz {
    private   CityService service = new CityService();
    private String url = "http://cdn.sojson.com/_city.json";
    private   String TAG = "CityBiz";
    public  void getCity() {
        CommonHttpClient.sendRequest(CommonReqeust.createGetReqeust(url, null), new CommonStringCallback(new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Gson gson = new Gson();
                List<City> cities =  gson.fromJson((String) responseObj,new TypeToken<List<City>>(){}.getType());
                Log.i(TAG, "onSuccess: "+cities);
                service.addCitys(cities);
            }
            @Override
            public void onFailure(Object responseObj) {
                Log.i(TAG, "");
            }
        })));
    }
    //获取省份信息x
    public void getProvinces() {
        boolean flag = (boolean) SPUtils.getInstance().get(Config.CITY_KEY,Boolean.FALSE);
        if(flag){
            service.getPrivince();
        }else{
            T.showToast("当前数据加载中，请稍片刻~");
        }

    }
    //城市
    public void getCitys(City city){
     service.getCity(city);
    }
}
