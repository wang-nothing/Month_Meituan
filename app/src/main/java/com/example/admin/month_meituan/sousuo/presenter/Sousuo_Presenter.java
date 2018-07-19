package com.example.admin.month_meituan.sousuo.presenter;

import com.example.admin.month_meituan.bean.SousuoBean;
import com.example.admin.month_meituan.sousuo.model.Sousuo_Imodel;
import com.example.admin.month_meituan.sousuo.model.Sousuo_Model;
import com.example.admin.month_meituan.sousuo.view.Sousuo_Iview;

public class Sousuo_Presenter implements Sousuo_Ipresenter {
    private Sousuo_Iview sousuo_iview;
    private Sousuo_Model sousuo_model;

    public Sousuo_Presenter(Sousuo_Iview sousuo_iview) {
        this.sousuo_iview = sousuo_iview;
        this.sousuo_model = new Sousuo_Model();
    }
    public void getdatas(String ss){
        sousuo_model.getdata(new Sousuo_Imodel() {
            @Override
            public void suosou_Imodel_onSuccess(SousuoBean sousuoBean) {
                sousuo_iview.suosou_onSuccess(sousuoBean);
            }

            @Override
            public void sousuo_Imodel_onFail(int code) {
                sousuo_iview.sousuo_onFail(code);
            }
        },ss);
    }
    @Override
    public void suosou_Imodel_onDestroys() {
        if (sousuo_iview != null){
            sousuo_iview = null;
        }
    }
}
