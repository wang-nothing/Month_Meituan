package com.example.admin.month_meituan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.bean.AddressBean;
import com.example.admin.month_meituan.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView address_back;
    private EditText address_et;
    private Button address_btn,address_location;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initViews();
        initDatas();
        initListener();
    }

    private void initListener() {
        address_location.setOnClickListener(this);
        address_back.setOnClickListener(this);
    }

    private void initDatas() {
        OkHttpUtils.doGet("http://39.108.3.12:3000/v1/location", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(json, AddressBean.class);
                address = addressBean.getData().getAddress();
                Log.i("TAG", "onResponse: "+address);
            }
        });
    }

    private void initViews() {
        address_back = findViewById(R.id.address_back);
        address_et = findViewById(R.id.address_et);
        address_btn = findViewById(R.id.address_btn);
        address_location = findViewById(R.id.address_location);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.address_back:
                finish();
                break;

            case R.id.address_location:
                Intent i = new Intent();
                i.putExtra("bian",address);
                setResult(2,i);
                finish();
                break;
        }
    }
}
