package com.example.admin.month_meituan.Home.presenter;

import com.example.admin.month_meituan.Home.model.Home_Model;
import com.example.admin.month_meituan.Home.model.Imodel;
import com.example.admin.month_meituan.Home.view.Iview;
import com.example.admin.month_meituan.bean.Home_GoodsBean;

public class Home_Presenter implements Ipresenter {
    private Iview iview;
    private Home_Model home_model;

    public Home_Presenter(Iview iview) {
        this.iview = iview;
        this.home_model = new Home_Model();
    }

    public void getdatas(int page){
        home_model.getdata(new Imodel() {
            @Override
            public void onSuccess(Home_GoodsBean home_goodsBean) {
                iview.view_onSuccess(home_goodsBean);
            }

            @Override
            public void onFail(int code) {
                iview.view_onFail(code);
            }
        },page);
    }

    @Override
    public void onDestroys() {
        if (iview != null){
            iview = null;
        }
    }
}
