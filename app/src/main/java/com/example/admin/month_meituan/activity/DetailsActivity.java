package com.example.admin.month_meituan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.month_meituan.Home.presenter.Home_Presenter;
import com.example.admin.month_meituan.Home.view.Iview;
import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.adapter.ListViewAdapter;
import com.example.admin.month_meituan.adapter.RecyclerAdapter;
import com.example.admin.month_meituan.bean.Home_GoodsBean;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements Iview, View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView details_tv;
    private TextView details_back;
    private ListView details_list;
    private int page = 0;
    private List<Home_GoodsBean.DataBean> data;
    private Home_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        initViews();
        initListener();
        initDatas();
        presenter = new Home_Presenter(this);
        presenter.getdatas(page);
    }

    private void initListener() {
        details_back.setOnClickListener(this);
        details_list.setOnItemClickListener(this);
    }

    private void initViews() {
        details_tv = findViewById(R.id.details_tv);
        details_back = findViewById(R.id.details_back);
        details_list = findViewById(R.id.details_list);
    }

    private void initDatas() {
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        details_tv.setText(text);

    }

    @Override
    public void view_onSuccess(final Home_GoodsBean home_goodsBean) {
        data = home_goodsBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListViewAdapter listViewAdapter = new ListViewAdapter(DetailsActivity.this, data);
                details_list.setAdapter(listViewAdapter);
            }
        });
    }

    @Override
    public void view_onFail(int code) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.details_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(DetailsActivity.this,""+data.get(i).getName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DetailsActivity.this, FoodActivity.class);
        startActivity(intent);
    }
}
