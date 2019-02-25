package com.hehen.heweater.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.hehen.heweater.MainActivity;
import com.hehen.heweater.R;
import com.hehen.heweater.bean.City;
import com.hehen.heweater.biz.CityBiz;
import com.hehen.heweater.config.Config;
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
    /**
     * 选中城市返回首页
     */
    public void toMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(Config.INTENT_CITY_KEY, selecedCounty);  //选中城市
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //点击ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selecedProvince = provinceList.get(position);  //选中省份
                    queryCity();
                } else if (currentLevel == LEVEL_CITY) {
                    selecedCity = cityList.get(position);
                    queryCounty();
                } else if (currentLevel == LEVEL_COUNTY) {
                    selecedCounty = countyList.get(position); //选中区域
                    toMainActivity();  //返回首页
                }
                adapter.notifyDataSetChanged();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                if (currentLevel == LEVEL_COUNTY) {
                    currentLevel = LEVEL_CITY;
                    queryCity();
                } else if (currentLevel == LEVEL_CITY) {
                    currentLevel = LEVEL_PROVINCE;
                    queryProvince();
                } else {
                    getActivity().finish();
                }
                adapter.notifyDataSetChanged();
            }
        });
        queryProvince();
    }

    private void queryCounty() {
        currentLevel = LEVEL_COUNTY;
        //获取县级数据
        if (selecedCity != null) {
            title_text.setText(selecedCity.getCity_name());
        }
        cityBiz.getCountys(selecedCity);
        countyList = DataUtils.getInstance().getCountys();
        //当前城市为空的则直接返回当前当前选中城市
        if(countyList.isEmpty()){
            selecedCounty = selecedCity;
            toMainActivity();
        }
        dataList.clear();
        for (City city : countyList) {
            dataList.add(city.getCity_name());
        }
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
        currentLevel = LEVEL_CITY;
        cityBiz.getCitys(selecedProvince);
        cityList = DataUtils.getInstance().getCity();
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
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        currentLevel = 0;
    }
}

