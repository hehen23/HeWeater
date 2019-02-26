package com.hehen.henweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author chenping
 * @date 2019/2/27 2:14 AM
 * @Description:
 */
public class ChooseItemActivity extends AppCompatActivity {
    private ChooseFragment chooseFragment = new ChooseFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.id_fragment_main,chooseFragment);
        transaction.commit();
        transaction.show(chooseFragment);
    }
}
