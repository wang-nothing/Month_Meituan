package com.example.admin.month_meituan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.admin.month_meituan.Holder.RecyclerHolder;
import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.bean.Home_GoodsBean;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private Context context;
    private List<Home_GoodsBean.DataBean> data;

    public RecyclerAdapter(Context context, List<Home_GoodsBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_adapter_item, viewGroup, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int i) {
        recyclerHolder.home_item_title.setText(data.get(i).getName());
        recyclerHolder.home_item_month_sales_tip.setText(data.get(i).getMonth_sales_tip());
        recyclerHolder.home_item_distance.setText(data.get(i).getDelivery_time_tip()+"/"+data.get(i).getDistance());
        recyclerHolder.home_item_min_price_tip.setText(data.get(i).getMin_price_tip()+"|"+data.get(i).getShipping_fee_tip()+"|"+data.get(i).getAverage_price_tip());
        recyclerHolder.home_item_discounts2_imfo1.setText(data.get(i).getDiscounts2().get(0).getInfo());
        recyclerHolder.home_item_discounts2_imfo2.setText(data.get(i).getDiscounts2().get(1).getInfo());
        Glide.with(context).load(data.get(i).getPic_url()).into(recyclerHolder.home_item_image);
        Glide.with(context).load(data.get(i).getDiscounts2().get(0).getIcon_url()).into(recyclerHolder.home_item_discounts2_icon1);
        Glide.with(context).load(data.get(i).getDiscounts2().get(1).getIcon_url()).into(recyclerHolder.home_item_discounts2_icon2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
