package com.hehen.heweater.utils.okhttp.callback;

import android.os.Handler;
import android.os.Looper;

import com.hehen.heweater.utils.okhttp.exception.OkHttpException;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataHandle;
import com.hehen.heweater.utils.okhttp.listener.DisposeDataListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author chenping
 * @date 2019/2/25 1:32 PM
 * @Description:
 */
public class CommonStringCallback implements Callback {
    private Handler mDeliveryHanler; //消息转发
    private DisposeDataListener mListener;
    private  DisposeDataHandle dataHandle;

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OKHER_ERROR = -3;
    //与服务器发挥字段的一个对应的关系
    protected final String RESULT_CODE = "ecode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROE_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    public CommonStringCallback(DisposeDataHandle handle) {
            this.mListener = handle.mLister;
            this.mDeliveryHanler = new Handler(Looper.getMainLooper());
        }
    @Override
    public void onFailure(Call call, IOException e) {
        mListener.onFailure(e);
    }
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response == null) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        String result = response.body().string();
        mListener.onSuccess(result);
    }
}
