package com.hehen.heweater.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author chenping
 * @date 2019/2/21 10:34 AM
 * @Description:
 */
public class T {
    private static Toast toast;
    public static void showToast(String content){
        toast.setText(content);
        toast.show();
    }
    public static void init(Context context){
        toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }
}
