package com.example.admin.month_meituan.sousuo.model;

import com.example.admin.month_meituan.bean.SousuoBean;

public interface Sousuo_Imodel {
    void suosou_Imodel_onSuccess(SousuoBean sousuoBean);

    void sousuo_Imodel_onFail(int code);
}
