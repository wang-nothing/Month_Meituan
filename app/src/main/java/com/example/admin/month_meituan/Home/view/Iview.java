package com.example.admin.month_meituan.Home.view;

import com.example.admin.month_meituan.bean.Home_GoodsBean;

public interface Iview {
    void view_onSuccess(Home_GoodsBean home_goodsBean);

    void view_onFail(int code);
}
