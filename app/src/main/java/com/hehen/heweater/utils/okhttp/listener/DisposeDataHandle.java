package com.hehen.heweater.utils.okhttp.listener;

/**
 * @Description
 * @auther ping
 * @create 2019-02-07 9:28 AM
 * @function "json转换字节码转换"
 */
public class DisposeDataHandle {
    public DisposeDataListener mLister = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener listener){
        this.mLister = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener,Class<?> cls){
        this.mLister = listener;
        this.mClass = cls;
    }

}
