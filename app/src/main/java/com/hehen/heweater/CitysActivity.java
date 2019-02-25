package com.hehen.heweater;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hehen.heweater.fragment.ProvincesFragment;

public class CitysActivity extends AppCompatActivity {
    private ProvincesFragment fragment = new ProvincesFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citys);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.id_fragment_main,fragment);
        transaction.commit();
        transaction.show(fragment);
    }
}
