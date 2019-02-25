package com.hehen.heweater.utils.okhttp.callback;

import android.os.Handler;
import android.os.Looper;


import com.hehen.heweater.utils.okhttp.exception.OkHttpException;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataHandle;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Description
 * @auther ping
 * @create 2019-02-07 9:33 AM
 * @function "JSONCallBack"
 */
public class CommonJsonCallback implements Callback {
    //与服务器发挥字段的一个对应的关系
    protected final String RESULT_CODE = "ecode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROE_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OKHER_ERROR = -3;

    private Handler mDeliveryHanler; //消息转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mLister;
        this.mClass = handle.mClass;
        this.mDeliveryHanler = new Handler(Looper.getMainLooper());
    }

    /**
     * 请求失败处理
     *
     * @param call
     * @param e
     */
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHanler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    /**
     * 成功响应处理函数
     *
     * @param call
     * @param response
     * @throws IOException
     */
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHanler.post(new Runnable() {
            @Override
            public void run() {
                handlerRespose(result);
            }
        });
    }

    /**
     * 处理服务器返回的数据
     *
     * @param resultObj
     */
    private void handlerRespose(Object resultObj) {
        if (resultObj == null) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(resultObj.toString());
            if (result.has(RESULT_CODE)) {
                //从json对象中取出我们的响应吗,若为0，则正确响应
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if(mClass == null) {
                        mListener.onSuccess(resultObj);
                    }else{
                        //TODO 即需要我们将json对象转换为实体对象
                        Object obj = null ; // TODO  需要实现实体工具类 ResponseEctityToModule.paraseJosnObjectToMoudle(result,mClass);
                        if(obj != null){
                            mListener .onSuccess(obj);
                        }else{
                            //返回不合法的josn
                            mListener.onFailure(new OkHttpException(JSON_ERROR,result.get(RESULT_CODE)));
                        }
                    }
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR,e.getMessage()));
        }
    }
}
