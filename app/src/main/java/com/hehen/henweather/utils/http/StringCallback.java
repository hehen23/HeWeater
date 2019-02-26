package com.hehen.henweather.utils.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author chenping
 * @date 2019/2/27 1:38 AM
 * @Description:
 */
public abstract class StringCallback implements Callback {
    @Override
    public void onFailure(Call call, IOException e) {
        onError(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()){
            String body = response.body().string();
            onSuccess(body);
        }
    }

    public  abstract void onError(Exception e);
    public abstract void onSuccess(String body);

}
