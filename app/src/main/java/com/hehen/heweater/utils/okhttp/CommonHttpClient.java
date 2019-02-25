package com.hehen.heweater.utils.okhttp;

import com.hehen.heweater.utils.okhttp.ssl.HttpSSLUtil;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Description
 * @auther ping
 * @create 2019-02-05 10:26 PM
 * @function "功能说明"
 */
public class CommonHttpClient {
    private static final int TIME_OUT = 30; //超时时间
    private static OkHttpClient mHttpClient;
    //为我们的client设置参数
    static {
        OkHttpClient.Builder mOkHttpBuilder = new OkHttpClient.Builder();
        //为构建者填充超时时间
        mOkHttpBuilder.connectTimeout(TIME_OUT,TimeUnit.SECONDS);
        mOkHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        mOkHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        //允许重定向
        mOkHttpBuilder.followRedirects(true);
        //支持https支持
        mOkHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        mOkHttpBuilder.sslSocketFactory(HttpSSLUtil.getSslSoketFactory());
        mHttpClient = mOkHttpBuilder.build();
    }
    /**
     * 用于发送https http请求
     * @param request
     * @param commonCallback
     * @return
     */
    public static Call sendRequest(Request request, Callback commonCallback){
        Call mCall = mHttpClient.newCall(request);
        mCall.enqueue(commonCallback);
        return mCall;
    }
}