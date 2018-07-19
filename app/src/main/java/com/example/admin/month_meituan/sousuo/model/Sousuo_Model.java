package com.example.admin.month_meituan.sousuo.model;

import android.util.Log;

import com.example.admin.month_meituan.bean.SousuoBean;
import com.example.admin.month_meituan.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sousuo_Model {
    private String url_ss = "http://39.108.3.12:3000/v1/search/restaurant?keyword=";
    private OkHttpClient client;
    public void getdata(final Sousuo_Imodel sousuo_imodel,String ss){
        OkHttpUtils.doGet(url_ss+ss, new Callback() {

            private String string;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG", "onFailure: "+"失败");
                sousuo_imodel.sousuo_Imodel_onFail(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                string = response.body().string();
                Log.d("TAG", "onResponse: "+string);
                sousuo_imodel.suosou_Imodel_onSuccess(new Gson().fromJson(string, SousuoBean.class));
            }
        });
    }
}
