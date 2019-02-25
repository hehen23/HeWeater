package com.hehen.heweater.utils.okhttp.exception;

/**
 * @Description
 * @auther ping
 * @create 2019-02-07 9:48 AM
 * @function "自定义异常类"
 */
public class OkHttpException  extends Exception{

    /**
     * 异常码
     */
    private int ecode;
    /**
     * 异常消息
     */
    private  Object emsg;

    public OkHttpException(int ecode, Object e) {
        this.ecode = ecode;
        this.emsg = e;
    }
    public int getEcode(){
        return ecode;
    }
    public Object getEmsg(){
        return emsg;
    }
}
