package com.example.admin.month_meituan.bean;

import java.io.Serializable;

public class ProductListBean implements Serializable {
    private String proName;
    private int imgUrl;

    public ProductListBean(String proName, int imgUrl) {
        this.proName = proName;
        this.imgUrl = imgUrl;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
