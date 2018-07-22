package com.example.admin.month_meituan.shop.model;

import android.util.Log;

import com.example.admin.month_meituan.bean.StoreBean;
import com.example.admin.month_meituan.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Food_Model {
    public void getdata(final Imodel_Food imodel_food, String id){
        OkHttpUtils.doGet("http://39.108.3.12:3000/v1/restaurant/" + id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                imodel_food.food_onFail(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                StoreBean storeBean = gson.fromJson(json, StoreBean.class);
                imodel_food.food_onSuccess(storeBean);
                Log.i("TAG", "onResponse: ~~~~~~~~~~~~~~~~"+storeBean);
            }
        });
    }
}
