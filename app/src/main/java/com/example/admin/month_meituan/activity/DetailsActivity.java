package com.example.admin.month_meituan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.admin.month_meituan.Home.presenter.Home_Presenter;
import com.example.admin.month_meituan.Home.view.Iview;
import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.adapter.RecyclerAdapter;
import com.example.admin.month_meituan.bean.Home_GoodsBean;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements Iview, View.OnClickListener {
    private TextView details_tv;
    private TextView details_back;
    private PullToRefreshScrollView scrollView;
    private RecyclerView recyclerView;
    private Home_Presenter presenter;
    private int page = 0;
    private List<Home_GoodsBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        initViews();
        initListener();
        initDatas();
    }

    private void initListener() {
        details_back.setOnClickListener(this);
    }

    private void initViews() {
        details_tv = findViewById(R.id.details_tv);
        details_back = findViewById(R.id.details_back);
        scrollView = findViewById(R.id.scrollView);
        recyclerView = findViewById(R.id.recyclerview);
        scrollView.setMode(PullToRefreshScrollView.Mode.BOTH);
        ILoadingLayout proxy = scrollView.getLoadingLayoutProxy(true, false);
        proxy.setPullLabel("向下拖动完成刷新...");
        proxy.setRefreshingLabel("正在加载新数据...");
        proxy.setReleaseLabel("释放完成刷新...");

        //设置底部刷新文字
        ILoadingLayout footLayout = scrollView.getLoadingLayoutProxy(false, true);
        footLayout.setPullLabel("向上拽动完成刷新...");
        footLayout.setRefreshingLabel("正在疯刷新数据...");
        footLayout.setReleaseLabel("松开完成刷新...");
        presenter = new Home_Presenter(this);
        presenter.getdatas(page);

    }

    private void initDatas() {
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        details_tv.setText(text);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                presenter.getdatas(page);
                scrollView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                presenter.getdatas(page++);
                scrollView.onRefreshComplete();
            }
        });
    }

    @Override
    public void view_onSuccess(Home_GoodsBean home_goodsBean) {
        data = home_goodsBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(DetailsActivity.this, data);
                recyclerView.setAdapter(recyclerAdapter);
                scrollView.onRefreshComplete();
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
}
