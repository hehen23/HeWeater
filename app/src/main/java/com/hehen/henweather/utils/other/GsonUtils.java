package com.hehen.henweather.utils.other;

import com.google.gson.Gson;

/**
 * @author chenping
 * @date 2019/2/27 1:46 AM
 * @Description:
 */
public class GsonUtils {
    private static  Gson gson = null;
    public static Gson getGson(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }
}
