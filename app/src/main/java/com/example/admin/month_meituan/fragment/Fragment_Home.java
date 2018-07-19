package com.example.admin.month_meituan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.month_meituan.Home.presenter.Home_Presenter;
import com.example.admin.month_meituan.Home.view.Iview;
import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.activity.AddressActivity;
import com.example.admin.month_meituan.activity.SearchActivity;
import com.example.admin.month_meituan.adapter.MyGridViewAdapter;
import com.example.admin.month_meituan.adapter.MyViewPagerAdapter;
import com.example.admin.month_meituan.adapter.RecyclerAdapter;
import com.example.admin.month_meituan.bean.Home_GoodsBean;
import com.example.admin.month_meituan.bean.ProductListBean;
import com.example.admin.month_meituan.custom.MyTitle;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment implements ViewPager.OnPageChangeListener, Iview, View.OnClickListener {
    private static final int CODE_UPDATE_HOME_LIST = 1;
    private ViewPager viewpager;
    private int totalPage;//总的页数
    private int mPageSize = 8;//每页显示的最大数量
    private List<ProductListBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private PullToRefreshScrollView scollview;
    private RecyclerView recyclerview;
    private int currentPage;//当前页
    private int page = 0;
    private MyTitle home_tv;
    private Button home_address;

    private String[] proName = {"美食","美团超市","生鲜果蔬","下午茶","正餐优选","汉堡披萨",
            "跑腿代购","快餐简餐","地方菜","炸鸡","免配送费"};
    private int[] image = new int[] {R.drawable.meishi,R.drawable.mtcs,R.drawable.sxgs,R.drawable.xwc,R.drawable.zcyx,R.drawable.hbps,
            R.drawable.ptdg,R.drawable.kcjc,R.drawable.dfc,R.drawable.zjms,R.drawable.mpsf};
    private View view;
    private int imageBackground;
    private Home_Presenter presenter;
    private List<Home_GoodsBean.DataBean> data = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_UPDATE_HOME_LIST:
                    Home_GoodsBean bean = (Home_GoodsBean) msg.obj;
                    data.addAll(bean.getData());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniViews();
        initListener();
        //模拟数据源
        setDatas();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for(int i=0;i<totalPage;i++){
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview_layout,viewpager,false);
            gridView.setAdapter(new MyGridViewAdapter(getActivity(),listDatas,i,mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + currentPage * mPageSize;
                    Log.i("TAG","position的值为："+position + "-->pos的值为："+pos);
                    Toast.makeText(getActivity(),"你点击了 "+listDatas.get(pos).getProName(),Toast.LENGTH_SHORT).show();

                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //小圆点指示器

        //设置ViewPager滑动监听
        viewpager.addOnPageChangeListener(this);
    }

    private void initListener() {
        home_tv.setOnClickListener(this);
        home_address.setOnClickListener(this);
    }

    private void setDatas() {
        listDatas = new ArrayList<>();
        for(int i=0;i<proName.length;i++){
            listDatas.add(new ProductListBean(proName[i],image[i]));
        }
        presenter = new Home_Presenter(this);
        presenter.getdatas(page);
        scollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                presenter.getdatas(page);
                scollview.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                presenter.getdatas(page++);
                scollview.onRefreshComplete();
            }
        });
    }

    private void iniViews() {
        home_address = view.findViewById(R.id.home_address);
        home_tv = view.findViewById(R.id.home_tv);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapter(getActivity(), data);
        recyclerview.setAdapter(adapter);
        scollview = view.findViewById(R.id.scollview);
        scollview.setMode(PullToRefreshScrollView.Mode.BOTH);
        ILoadingLayout proxy = scollview.getLoadingLayoutProxy(true, false);
        proxy.setPullLabel("向下拖动完成刷新...");
        proxy.setRefreshingLabel("正在加载新数据...");
        proxy.setReleaseLabel("释放完成刷新...");

        //设置底部刷新文字
        ILoadingLayout footLayout = scollview.getLoadingLayoutProxy(false, true);
        footLayout.setPullLabel("向上拽动完成刷新...");
        footLayout.setRefreshingLabel("正在疯刷新数据...");
        footLayout.setReleaseLabel("松开完成刷新...");
        footLayout.setLoadingDrawable(getResources().getDrawable(R.drawable.timg));

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        currentPage = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void view_onSuccess(Home_GoodsBean home_goodsBean) {
        Message message = Message.obtain();
        message.what = CODE_UPDATE_HOME_LIST;
        message.obj = home_goodsBean;
        mHandler.sendMessage(message);

    }

    @Override
    public void view_onFail(int code) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_tv:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.home_address:
                Intent forResult = new Intent(getActivity(), AddressActivity.class);
                startActivityForResult(forResult,1);
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data1) {
        super.onActivityResult(requestCode, resultCode, data1);
        if (requestCode == 1 && resultCode == 2){
            String bian = data1.getStringExtra("bian");
            home_address.setText(bian);
        }
    }
}
