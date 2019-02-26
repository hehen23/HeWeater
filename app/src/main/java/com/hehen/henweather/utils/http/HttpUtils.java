package com.hehen.henweather.utils.http;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author chenping
 * @date 2019/2/27 1:27 AM
 * @Description:
 */
public class HttpUtils {
    private static final int TIME_OUT = 30; //超时时间
    private static OkHttpClient mHttpClient;
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置超时时间
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        //允许重定向
        builder.followRedirects(true);
        mHttpClient = builder.build();
    }
    public static Call sendRequest(Request request, Callback commonCallback) {
        Call mCall = mHttpClient.newCall(request);
        mCall.enqueue(commonCallback);
        return mCall;
    }
}
