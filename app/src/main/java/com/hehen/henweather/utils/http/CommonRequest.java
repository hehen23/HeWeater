package com.hehen.henweather.utils.http;

import java.util.Map;

import okhttp3.Request;

/**
 * @author chenping
 * @date 2019/2/27 1:31 AM
 * @Description:
 */
public class CommonRequest  {
    public static Request createRequestGet(String url,RequestParams requestParams){
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        if(requestParams != null){
            for(Map.Entry<String,String> entry:requestParams.urlParams.entrySet()){
                builder.append(entry.getKey()).append(entry.getValue());
                builder.append("&");
            }
            builder.substring(0,builder.length()-1);
        }
        return  new Request.Builder().url(url).get().build();
    }
}
