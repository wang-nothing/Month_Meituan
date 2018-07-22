package com.example.admin.month_meituan.shop.view;

import com.example.admin.month_meituan.bean.StoreBean;

public interface Iview_food {
    void food_onSuccess(StoreBean storeBean);

    void food_onFail(int code);
}
