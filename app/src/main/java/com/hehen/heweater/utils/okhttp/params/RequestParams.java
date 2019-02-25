package com.hehen.heweater.utils.okhttp.params;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @auther ping
 * @create 2019-02-05 9:54 PM
 * @function 封装请求到到map中
 */
public class RequestParams {
    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
    public ConcurrentHashMap<String, Object> fileparams = new ConcurrentHashMap<String, Object>();

    public RequestParams() {
        this(null);
    }

    public RequestParams(Map<String, String> mMap) {
        if (mMap != null) {
            for (Map.Entry<String, String> entry : mMap.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     *
     * @param  key String
     * @param  value String
     */
    public RequestParams(String key, String value) {
        put(key,value);
    }

    /**
     *
     * @param key
     * @param value
     * 添加参数: Stirng key,String value
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    /**
     *
     * @param key
     * @param value
     * @function 保存文件对象,进入hashMap
     */
    public void put(String key, Object value) {
        if (key != null) {
            fileparams.put(key, value);
        }else{
            throw new IllegalArgumentException("key to be null");
        }
    }
}
