package com.example.admin.month_meituan.shop.presenter;

import com.example.admin.month_meituan.bean.StoreBean;
import com.example.admin.month_meituan.shop.model.Food_Model;
import com.example.admin.month_meituan.shop.model.Imodel_Food;
import com.example.admin.month_meituan.shop.view.Iview_food;

public class Food_Presenter implements Iprensenter_Food {
    private Iview_food iview_food;
    private Food_Model food_model;

    public Food_Presenter(Iview_food iview_food) {
        this.iview_food = iview_food;
        this.food_model = new Food_Model();
    }
    public void getdatas(String id){
        food_model.getdata(new Imodel_Food() {
            @Override
            public void food_onSuccess(StoreBean storeBean) {
                iview_food.food_onSuccess(storeBean);
            }

            @Override
            public void food_onFail(int code) {
                iview_food.food_onFail(code);
            }
        },id);
    }
    @Override
    public void food_onDestroys() {
        if (iview_food != null){
            iview_food = null;
        }
    }
}
