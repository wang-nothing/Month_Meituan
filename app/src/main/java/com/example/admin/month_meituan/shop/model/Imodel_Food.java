package com.example.admin.month_meituan.shop.model;

import com.example.admin.month_meituan.bean.StoreBean;

public interface Imodel_Food {
    void food_onSuccess(StoreBean storeBean);

    void food_onFail(int code);
}
