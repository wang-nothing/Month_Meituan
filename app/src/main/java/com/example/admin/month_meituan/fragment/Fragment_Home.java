package com.example.admin.month_meituan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.adapter.MyGridViewAdapter;
import com.example.admin.month_meituan.adapter.MyViewPagerAdapter;
import com.example.admin.month_meituan.bean.ProductListBean;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager viewpager;
    private int totalPage;//总的页数
    private int mPageSize = 8;//每页显示的最大数量
    private List<ProductListBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private int currentPage;//当前页
    

    private String[] proName = {"美食","美团超市","生鲜果蔬","下午茶","正餐优选","汉堡披萨",
            "跑腿代购","快餐简餐","地方菜","炸鸡","免配送费"};
    private int[] image = new int[] {R.drawable.meishi,R.drawable.mtcs,R.drawable.sxgs,R.drawable.xwc,R.drawable.zcyx,R.drawable.hbps,
            R.drawable.ptdg,R.drawable.kcjc,R.drawable.dfc,R.drawable.zjms,R.drawable.mpsf};
    private View view;
    private int imageBackground;

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
            /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + currentPage*mPageSize;
                    Log.i("TAG","position的值为："+position + "-->pos的值为："+pos);
                    Toast.makeText(MainActivity.this,"你点击了 "+listDatas.get(pos).getProName(),Toast.LENGTH_SHORT).show();
                }
            });*/
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //小圆点指示器

        //设置ViewPager滑动监听
        viewpager.addOnPageChangeListener(this);
    }

    private void setDatas() {
        listDatas = new ArrayList<>();
        for(int i=0;i<proName.length;i++){
            listDatas.add(new ProductListBean(proName[i],image[i]));
        }
    }

    private void iniViews() {
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
