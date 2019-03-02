package com.hehen.henweather.utils;

/**
 * @author chenping
 * @date 2019/3/2 12:22 AM
 * @Description:
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限获取工具类
 * 引导用于进行手动设置
 */
public class PermissionUtils {
    private Activity mActivity;
    private Callback mCallback;
    private int mRequestCode;

    /**
     * 回调接口
     */
    public static interface Callback {
        void grantAll();  //成功授权
        void denied();  //退出
    }
    public PermissionUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requeryPermission(List<String> needPermission,
                                  int reqeustCode,
                                  Callback callback) {
        //api小于23直接返回默认获取权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callback.grantAll();
            return;
        }
        /**
         *Activity不能为空
         */
        if (mActivity == null) {
            throw new IllegalArgumentException("Activity is  null");
        }
        mRequestCode = reqeustCode;
        this.mCallback = callback;
        List<String> permission = new ArrayList<>();
        for (String mPermission : needPermission) {
            if (mActivity.checkSelfPermission(mPermission) != PackageManager.PERMISSION_GRANTED) {
                permission.add(mPermission);
            }
        }
        if (permission.isEmpty()) {
            callback.grantAll();
            return;
        }
        //处理未授予的权限
        mActivity.requestPermissions(permission.toArray(new String[]{}), reqeustCode);
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (mRequestCode == requestCode) {
            boolean grantAll = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    grantAll = false;
                    Toast.makeText(mActivity, permissions[i] + "未授权,应用无法正常使用", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if (grantAll) {
                mCallback.grantAll();
            } else {
                showDialogSetting();
                mCallback.denied();
            }
        }
    }

    /**
     * 显示Dialog提示设置权限
     */
    public void showDialogSetting() {
        new AlertDialog.Builder(mActivity).setNeutralButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setTitle("提示信息").setMessage("当前应用缺少必要权限,该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权.")
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startAppSettings();
                            }
                        }).create().show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
        mActivity.startActivity(intent);
    }
}

