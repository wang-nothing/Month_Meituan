package com.example.admin.month_meituan.my.login.model;

public interface Ilogin_Imodel {
    void model_onSuccess(String mobile, String password);

    void model_onFail(int code);
}
