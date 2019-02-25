package com.hehen.heweater.utils.okhttp.request;


import com.hehen.heweater.utils.okhttp.params.RequestParams;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @Description
 * @auther ping
 * @create 2019-02-05 9:51
 */
public class CommonReqeust {
    /**
     *创建post请求方法
     * @param url
     * @param params
     * @return 返回一个创建好的post请求
     */
    public static Request createPostRequest(String url, RequestParams params){
        FormBody.Builder mFofmBofy = new FormBody.Builder();
        if(params != null){
            for(Map.Entry<String,String> entry:params.urlParams.entrySet()){
               //将请求封装到请求构件中
                mFofmBofy.add(entry.getKey(),entry.getValue());
            }
        }
      FormBody mFofmBody = mFofmBofy.build();
        return new Request.Builder().url(url).post(mFofmBody).build();
    }

    /**
     * @param url
     * @param params
     * @return  返回创建好的个体请求
     */
    public static Request createGetReqeust(String url, RequestParams params){
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        //拼接参数
        if(params != null){
            for(Map.Entry<String,String> entry: params.urlParams.entrySet()){
                builder.append(entry.getKey()).append("=");
                builder.append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(builder.substring(0,builder.length()-1)).get().build();
    }
}
