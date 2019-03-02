package com.hehen.henweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.hehen.henweather.R;
import com.hehen.henweather.bean.Weather;
//import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author chenping
 * @date 2019/3/1 6:14 PM
 * @Description:
 */
public class ForecastAdapter extends ArrayAdapter<Weather> {
    private final int resourceId;
    private int icon_id;
    private List<Weather> weathers;

    public ForecastAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if (convertView == null) {
            viewHoler = new ViewHoler();
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHoler.tv_low = convertView.findViewById(R.id.id_tv_low);
            viewHoler.tv_week = convertView.findViewById(R.id.id_tv_week);
            viewHoler.tv_max = convertView.findViewById(R.id.id_tv_high);
            viewHoler.iv_icon = convertView.findViewById(R.id.id_iv_state);
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        Weather weather = getItem(position);
//        viewHoler.iv_icon.setImageResource(R.mipmap.sunny);

        if("晴".equals(weather.getType())){
            icon_id = R.mipmap.sunny;
        }else  if("雨".equals(weather.getType())){
            icon_id =R.mipmap.rain;
        }else if ("多云".equals(weather.getType())){
            icon_id = R.mipmap.cloudy;
        }else if("阴".equals(weather.getType())){
            icon_id = R.mipmap.overcast_sky;
        }else if ("小雨".equals(weather.getType())){
            icon_id = R.mipmap.b;
        }
      //  Picasso.get().load(icon_id).placeholder(R.mipmap.rain).into(viewHoler.iv_icon);
        Glide.with(getContext()).load(icon_id).into(viewHoler.iv_icon);
       // viewHoler.iv_icon.setImageResource(R.mipmap.sunny);
        viewHoler.tv_week.setText(weather.getDate() +"");
        viewHoler.tv_low.setText(weather.getLow() + "");
        viewHoler.tv_max.setText(weather.getHigh() + "");

        return convertView;
    }
    public class ViewHoler {
        public  TextView tv_week;  //星期几
        public ImageView iv_icon;  //天气状况图片
        public TextView tv_low;  //最低温度
        public TextView tv_max;   //最高温度

        public ViewHoler() {
        }
    }
}
