package com.example.admin.month_meituan.Home.model;

import com.example.admin.month_meituan.bean.Home_GoodsBean;

public interface Imodel {
    void onSuccess(Home_GoodsBean home_goodsBean);

    void onFail(int code);
}
