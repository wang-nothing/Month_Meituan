package com.example.admin.month_meituan.sousuo.view;

import com.example.admin.month_meituan.bean.SousuoBean;

public interface Sousuo_Iview {
    void suosou_onSuccess(SousuoBean sousuoBean);

    void sousuo_onFail(int code);
}
