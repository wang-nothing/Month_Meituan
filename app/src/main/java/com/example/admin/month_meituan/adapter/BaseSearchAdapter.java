package com.example.admin.month_meituan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.bean.SousuoBean;

import java.util.List;

public class BaseSearchAdapter extends BaseAdapter{
    private Context context;
    private List<SousuoBean.DataBean> sousuo;

    public BaseSearchAdapter(Context context, List<SousuoBean.DataBean> sousuo) {
        this.context = context;
        this.sousuo = sousuo;
    }

    @Override
    public int getCount() {
        return sousuo.size();
    }

    @Override
    public Object getItem(int i) {
        return sousuo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.suosou_item,null);
            holder = new Holder();
            holder.search_item_img = view.findViewById(R.id.search_item_img);
            holder.search_item_title = view.findViewById(R.id.search_item_title);
            holder.search_item_time = view.findViewById(R.id.search_item_time);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
            holder.search_item_time.setText(sousuo.get(i).getDelivery_time_tip());
            holder.search_item_title.setText(sousuo.get(i).getName());
            String pic_url = sousuo.get(i).getPic_url();
            Glide.with(context).load(pic_url).into(holder.search_item_img);
        return view;
    }

    class Holder{
        ImageView search_item_img;
        TextView search_item_title;
        TextView search_item_time;
    }
}
