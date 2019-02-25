package com.hehen.heweater.utils.okhttp.listener;

/**
 * @Description
 * @auther ping
 * @create 2019-02-07 9:24 AM
 * @function "自定义监听事件"
 */
public interface DisposeDataListener {
    /**
     * 请求成功
     * @param responseObj
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败
     * @param responseObj
     */
    public void onFailure(Object responseObj);
}
