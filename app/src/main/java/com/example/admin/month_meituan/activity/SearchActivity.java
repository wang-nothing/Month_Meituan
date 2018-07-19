package com.example.admin.month_meituan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.adapter.BaseSearchAdapter;
import com.example.admin.month_meituan.bean.SousuoBean;
import com.example.admin.month_meituan.sousuo.presenter.Sousuo_Presenter;
import com.example.admin.month_meituan.sousuo.view.Sousuo_Iview;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, Sousuo_Iview {
    private TextView search_back;
    private EditText search_et;
    private Button search_btn;
    private ListView search_lv;
    private String ss;
    private Sousuo_Presenter presenter;
    private List<SousuoBean.DataBean> sousuo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__search);
        initViews();
        initListener();
        presenter = new Sousuo_Presenter(this);

    }

    private void initListener() {
        search_et.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        search_back.setOnClickListener(this);
    }

    private void initDatas() {
        ss = search_et.getText().toString();
    }

    private void initViews() {
        search_back = findViewById(R.id.search_back);
        search_et = findViewById(R.id.search_et);
        search_lv = findViewById(R.id.search_lv);
        search_btn = findViewById(R.id.search_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back:
                finish();
                break;

            case R.id.search_btn:
                    initDatas();
                    if (!TextUtils.isEmpty(ss)){
                        presenter.getdatas(ss);
                        search_lv.setVisibility(view.VISIBLE);
                    }else {
                        search_lv.setVisibility(view.INVISIBLE);
                    }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.suosou_Imodel_onDestroys();
    }

    @Override
    public void suosou_onSuccess(final SousuoBean sousuoBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sousuo = sousuoBean.getData();
                Log.d("TAG", "run: "+sousuo);
                BaseSearchAdapter baseSearchAdapter = new BaseSearchAdapter(SearchActivity.this, sousuo);
                search_lv.setAdapter(baseSearchAdapter);
            }
        });
    }

    @Override
    public void sousuo_onFail(int code) {

    }
}
