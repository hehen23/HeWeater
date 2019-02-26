package com.hehen.henweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hehen.henweather.bean.City;
import com.hehen.henweather.biz.CityBiz;
import com.hehen.henweather.utils.data.DataUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * @author chenping
 * @date 2019/2/27 2:14 AM
 * @Description:
 */
public class ChooseFragment extends Fragment {
    public static final String KEY_SELECED_CITY = "key_selecedCity";

    @Override
    public void onStart() {
        super.onStart();
        biz.initLoad();
    }

    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;

    private CityBiz biz = new CityBiz();
    private int currentLevel = 0;
    private City selecedCity;  //选中城市
    private City selecedProvince;  //选中省份
    private City selecedCounty;  //选中县区

    private List<City> cityList = new ArrayList<>();
    private List<City> provinceList = new ArrayList<>();
    private List<City> countyList = new ArrayList<>();

    private ListView listView;
    private Button back;
    private TextView title_text;

    private List<String> dataList = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        listView = view.findViewById(R.id.id_listview);
        back = view.findViewById(R.id.id_back_button);
        title_text = view.findViewById(R.id.id_title_text);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selecedProvince = provinceList.get(position);
                    queryCity();
                } else if (currentLevel == LEVEL_CITY) {
                    selecedCity = cityList.get(position);
                    queryCounty();
                }
            }
        });
        if (currentLevel == LEVEL_PROVINCE) queryProvince();
    }

    private void toResultMainActivity() {
        getActivity().setResult(2003);
        getActivity().finish();
    }

    private void queryProvince() {
        title_text.setText("中国");
        currentLevel = LEVEL_PROVINCE;
        if (DataUtils.mProvince == null || DataUtils.mProvince.isEmpty()) {
            //数据库中获取
            List<City> cities = biz.getProvince();
            if (cities != null) {
                provinceList.clear();
                provinceList.addAll(cities);
            }
        } else {
            provinceList.clear();
            provinceList.addAll(DataUtils.mProvince);
            Log.i(TAG, "queryProvince: " + DataUtils.mProvince);
        }
        dataList.clear();
        for (City city : provinceList) {
            dataList.add(city.getCity_name());
            Log.i(TAG, "queryProvince: 来这里了么？？？");
        }
        adapter.notifyDataSetChanged();
    }
    private void queryCounty() {
        currentLevel = LEVEL_COUNTY;
        title_text.setText(selecedCity.getCity_name());
        List<City> list = biz.getCount(selecedCity);
        if (list != null || !list.isEmpty()) {
            countyList.clear();
            countyList.addAll(list);
            dataList.clear();
            for (City city : countyList) {
                dataList.add(city.getCity_name());
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void queryCity() {
        currentLevel = LEVEL_CITY;
        title_text.setText(selecedProvince.getCity_name());
        List<City> list = biz.getcities(selecedProvince);
        if (list != null) {
            cityList.clear();
            cityList.addAll(list);
            dataList.clear();
            for (City city : list) {
                dataList.add(city.getCity_name());
            }
        }
        adapter.notifyDataSetChanged();

    }
}
