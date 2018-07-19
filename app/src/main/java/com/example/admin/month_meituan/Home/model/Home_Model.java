package com.example.admin.month_meituan.Home.model;


import android.util.Log;

import com.example.admin.month_meituan.bean.Home_GoodsBean;
import com.example.admin.month_meituan.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class Home_Model {
    private OkHttpClient client;
    private String url_home = "http://39.108.3.12:3000/v1/restaurants?offset=";
    private String url_home_01 = "&limit=5&lng=116.29845&lat=39.95933";
    private String string;

    public void getdata(final Imodel imodel, int page){
        OkHttpUtils.doGet(url_home + page + url_home_01, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: "+"失败");
                imodel.onFail(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                string = response.body().string();
                Log.i(TAG, "onResponse: "+string.toString());
                imodel.onSuccess(new Gson().fromJson(string, Home_GoodsBean.class));
            }
        });
    }
}
