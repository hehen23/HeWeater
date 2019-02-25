package com.hehen.heweater.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hehen.heweater.R;
import com.hehen.heweater.bean.City;
import com.hehen.heweater.biz.CityBiz;
import com.hehen.heweater.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * @author chenping
 * @date 2019/2/25 6:47 PM
 * @Description:
 */
public class ProvincesFragment extends Fragment {
    private CityBiz cityBiz = new CityBiz();

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ArrayAdapter<String> adapter;
    private List<City> provinceList;  //省份
    private List<City> cityList;  //市列表
    private List<City> countyList; //县列表
    //选中的省
    private City selecedProvince;
    //选中的市
    private City selecedCity;
    //选中的县
    private City selecedCounty;
    private ListView listView;

    private TextView title_text;
    private Button back_btn;

    private static int currentLevel = 0;  //默认等级省
    private List<String> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.provinces_fragment, null);
        listView = view.findViewById(R.id.id_listview);
        title_text = view.findViewById(R.id.title_text);
        back_btn = view.findViewById(R.id.back_button);
        if (currentLevel == LEVEL_PROVINCE) {
            //弹出对话框加载中
            queryProvince();
        }
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //点击ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentLevel == LEVEL_PROVINCE) {
                    Log.i(TAG, "onItemClick: " + dataList.get(position));
                    selecedProvince = provinceList.get(position);  //选中省份
                    Log.i(TAG, "onItemClick: "+selecedProvince);
                    //设置选中
                    // queryProvince();
                    queryCity();
                    currentLevel = 1;
                    adapter.notifyDataSetChanged();
                } else if (currentLevel == LEVEL_CITY) {
                    selecedCity = cityList.get(position);
                    queryCounty();
                    Log.i(TAG, "onItemClick:省份 "+LEVEL_CITY + provinceList.get(position));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
            }
        });

    }
    private void queryCounty() {

    }
    //查询省份数据
    public void queryProvince() {
        provinceList = DataUtils.getInstance().getProvinces();
        if (!provinceList.isEmpty()) {
            dataList.clear();  //清空列表中的数据
            if (selecedProvince != null) {
                title_text.setText(selecedProvince.getCity_name());
            } else {
                title_text.setText("中国");
            }
            for (City city : provinceList) {
                dataList.add(city.getCity_name());
                listView.setSelection(0);
            }
        }
        Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
    }
    //查询市级数据
    public void queryCity() {
        cityBiz.getCitys(selecedProvince);
        cityList = DataUtils.getInstance().getCity();
        Log.i(TAG, "queryCity: "+cityList);
        Log.i(TAG, "queryCity: "+cityList);
        if (cityList == null) {
            return;
        }
        if (!cityList.isEmpty()) {
            dataList.clear();
            title_text.setText(selecedProvince.getCity_name());
            for (City city : cityList) {
                dataList.add(city.getCity_name());
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        currentLevel = 0;
    }
}

